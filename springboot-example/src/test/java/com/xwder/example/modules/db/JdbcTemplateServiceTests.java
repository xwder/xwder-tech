package com.xwder.example.modules.db;

import com.xwder.example.modules.dbtest.service.JdbcTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author xwder
 * @date 2021年3月11日
 */
@Slf4j
@SpringBootTest
public class JdbcTemplateServiceTests {

    @Autowired
    private JdbcTemplateService jdbcTemplateService;

    @Test
    public void jdbcTemplateQueryTest() {
       jdbcTemplateService.jdbcTemplateQuery();
    }

    @Test
    public void jdbcTemplateSaveTest() {
        jdbcTemplateService.jdbcTemplateSave();
    }
}
