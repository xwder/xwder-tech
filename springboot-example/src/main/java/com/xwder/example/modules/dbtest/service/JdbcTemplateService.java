package com.xwder.example.modules.dbtest.service;

import cn.hutool.core.lang.Assert;
import cn.hutool.json.JSONUtil;
import com.xwder.example.config.db.ExtendJdbcTemplate;
import com.xwder.example.modules.dbtest.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * JdbcTemplate
 * 参考：
 *
 * @author xwder
 * @date 2021年3月11日
 */
@Slf4j
@Service
public class JdbcTemplateService {

    @Autowired
    private ExtendJdbcTemplate extendJdbcTemplate;

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * test query
     * 参考： https://spring.hhui.top/spring-blog/2019/04/12/190412-SpringBoot%E9%AB%98%E7%BA%A7%E7%AF%87JdbcTemplate%E4%B9%8B%E6%95%B0%E6%8D%AE%E6%9F%A5%E8%AF%A2%E4%B8%8A%E7%AF%87/
     *
     * @return query result
     */
    public void jdbcTemplateQuery() {
        String sql = "select * from user";
        List<Map<String, Object>> mapList = extendJdbcTemplate.queryForList(sql);
        log.info(mapList.toString());

        sql = "select * from user where id=1";
        Map<String, Object> map = extendJdbcTemplate.queryForMap(sql);
        log.info(map.toString());

        // 使用占位符替换方式查询
        // 使用queryForMap有个不得不注意的事项，就是如果查不到数据时，会抛一个异常出来，所以需要针对这种场景进行额外处理
        sql = "select * from user where id=?";
        map = extendJdbcTemplate.queryForMap(sql, new Object[]{1});
        log.info("QueryForMap by ? ans: " + map);

        // 指定传参类型, 通过传参来填充sql中的占位
        sql = "select * from user where id =?";
        map = extendJdbcTemplate.queryForMap(sql, 1);
        log.info("QueryForMap by ? ans: " + map);


        // queryForObject
        // 列名称不匹配的情况下使用 直接使用数字下标或者columnName来获取对应的值，这里就可以考虑使用反射方式来赋值，减少getter/setter
        User user = extendJdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setUserName(rs.getString("user_name"));
                return user;
            }
        }, 1);
        log.info("queryFroObject by RowMapper: " + user);

        // 更简单的方式，直接通过BeanPropertyRowMapper来实现属性的赋值，前提是sql返回的列名能正确匹配
        user = extendJdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), 1);
        log.info("queryForObject by BeanPropertyRowMapper: " + user);

        // 使用类名姿势 只能适用于 返回 一列数据 的情况  查询count、avg、sum等函数返回唯一值
        try {
            user = extendJdbcTemplate.queryForObject(sql, User.class, 1);
            log.info("queryForObject by requireType return: " + user);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * jdbcTemplate save
     * 参考： https://segmentfault.com/a/1190000018957484
     */
    public void jdbcTemplateSave() {
        KeyHolder keyHolder;
        String sql;

        // sql直接插入一条数据
        sql = "INSERT INTO user( open_id, github_user_name, user_name, password, nickname, is_manager, phone, email, sex, address, register_time, last_update_time, status, avatar, salt, login_ip, last_login_time, is_available, remark, gmt_create, gmt_modified) " +
                "VALUES ('76C91088EF7B42DFDAD53AEDE03B354E', 'xwder', 'xwder', 'xxxxxx', 'OneDay', 1, NULL, 'xxxxxx', NULL, NULL, '2020-07-24 15:39:43', '2020-07-24 15:39:43', 0, 'https://cdn.xwder.com/image/blog/xwder/1-20201010094334764.png', 'xxxxxx', NULL, '2021-03-10 19:21:52', 1, NULL, '2020-07-24 15:39:43', '2021-03-10 19:21:52') ";
        boolean saveResult = extendJdbcTemplate.update(sql) > 0;
        log.info("直接插入数据结果：{}", saveResult);

        // 参数替换方式插入
        sql = "INSERT INTO user( open_id, github_user_name, user_name, password, nickname, is_manager, phone, email, sex, address, register_time, last_update_time, status, avatar, salt, login_ip, last_login_time, is_available, remark, gmt_create, gmt_modified) " +
                "VALUES ('76C91088EF7B42DFDAD53AEDE03B354E', 'xwder', ?, ?, 'OneDay', 1, NULL, 'xxxxxx', NULL, NULL, '2020-07-24 15:39:43', '2020-07-24 15:39:43', 0, 'https://cdn.xwder.com/image/blog/xwder/1-20201010094334764.png', 'xxxxxx', NULL, '2021-03-10 19:21:52', 1, NULL, '2020-07-24 15:39:43', '2021-03-10 19:21:52') ";
        saveResult = extendJdbcTemplate.update(sql, "用户名", "密码") > 0;
        log.info("参数替换方式插入结果：{}", saveResult);

        // 通过Statement方式插入
        saveResult = extendJdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1, "用户名1");
                preparedStatement.setString(2, "密码1");
            }
        }) > 0;
        log.info("通过Statement方式插入结果1：{}", saveResult);

        String finalSql = sql;
        saveResult = extendJdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(finalSql);
                preparedStatement.setString(1, "用户名2");
                preparedStatement.setString(2, "密码2");
                return preparedStatement;
            }
        }) > 0;
        log.info("通过Statement方式插入结果2：{}", saveResult);

        // 插入并返回主键id
        keyHolder = new GeneratedKeyHolder();
        extendJdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                // 指定主键
                PreparedStatement preparedStatement = connection.prepareStatement(finalSql, new String[]{"id"});
                preparedStatement.setString(1, "用户名3");
                preparedStatement.setString(2, "密码3");
                return preparedStatement;
            }
        }, keyHolder);
        Assert.notNull(Objects.requireNonNull(keyHolder.getKey()));
        int id = keyHolder.getKey().intValue();
        log.info("插入并返回主键id,返回主键：{}", id);

        // 批量插入
        int[] ans = extendJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                if (i == 0) {
                    preparedStatement.setString(1, "用户名4");
                } else {
                    preparedStatement.setString(1, "用户名5");
                }
                preparedStatement.setString(2, "密码");
            }

            @Override
            public int getBatchSize() {
                return 2;
            }
        });
        log.info("批量插入：{}", JSONUtil.toJsonStr(ans));

        // 批量插入并返回主键id
        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        extendJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                if (i == 0) {
                    preparedStatement.setString(1, "用户名6");
                } else {
                    preparedStatement.setString(1, "用户名7");
                }
                preparedStatement.setString(2, "密码");
            }

            @Override
            public int getBatchSize() {
                return 2;
            }
        }, generatedKeyHolder);

        List<Map<String, Object>> objectMapList = generatedKeyHolder.getKeyList();
        List<Object> idList = objectMapList.stream().map(x -> x.get("GENERATED_KEY")).collect(Collectors.toList());
        log.info("批量插入并返回主键id-主键:{}", idList.toString());

        // NamedParameterJdbcTemplate
        sql = "insert into user (user_name,password) values (:userName,:password)";
        User user = new User();
        user.setUserName("Joe");
        user.setPassword("密码");
        SqlParameterSource sqlParameterSource = new BeanPropertySqlParameterSource(user);
        keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql, sqlParameterSource, keyHolder);
        int k = Objects.requireNonNull(keyHolder.getKey()).intValue();
        log.info("NamedParameterJdbcTemplate-插入数据返回主键-主键：{}",k);
    }

}
