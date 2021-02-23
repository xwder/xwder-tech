package com.xwder.example.springboot;

import com.xwder.example.springboot.config.configurationProperties.SystemInfoConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SpringbootExampleApplicationTests {

    @Autowired
    private SystemInfoConfig systemInfoConfig;

    @Test
    void contextLoads() {
    }



}
