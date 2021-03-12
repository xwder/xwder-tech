package com.xwder.example.config.db;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * @author xwder
 * @date 2021年3月12日
 */
public class ExtendJdbcTemplate extends JdbcTemplate {

    public ExtendJdbcTemplate(){}

    public ExtendJdbcTemplate(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * 批量保存并返回主键
     * @param sql
     * @param pss
     * @param generatedKeyHolder
     * @return
     * @throws DataAccessException
     */
    public int[] batchUpdate(final String sql, final BatchPreparedStatementSetter pss,
                             final KeyHolder generatedKeyHolder) throws DataAccessException {
        return execute(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                return conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            }
        }, ps -> {
            try {
                int batchSize = pss.getBatchSize();
                int totalRowsAffected = 0;
                int[] rowsAffected = new int[batchSize];
                List generatedKeys = generatedKeyHolder.getKeyList();
                generatedKeys.clear();
                ResultSet keys = null;
                for (int i = 0; i < batchSize; i++) {
                    pss.setValues(ps, i);
                    rowsAffected[i] = ps.executeUpdate();
                    totalRowsAffected += rowsAffected[i];
                    try {
                        keys = ps.getGeneratedKeys();
                        if (keys != null) {
                            RowMapper rowMapper = new ColumnMapRowMapper();
                            RowMapperResultSetExtractor rse = new RowMapperResultSetExtractor(rowMapper, 1);
                            generatedKeys.addAll(rse.extractData(keys));
                        }
                    } finally {
                        JdbcUtils.closeResultSet(keys);
                    }
                }
                if (logger.isDebugEnabled()) {
                    logger.debug("SQL batch update affected " + totalRowsAffected + " rows and returned " +
                            generatedKeys.size() + " keys");
                }
                return rowsAffected;
            } finally {
                if (pss instanceof ParameterDisposer) {
                    ((ParameterDisposer) pss).cleanupParameters();
                }
            }
        });
    }
}