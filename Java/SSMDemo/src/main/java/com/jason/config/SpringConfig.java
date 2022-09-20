package com.jason.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.jason.service")
@PropertySource("classpath:jdbc.properties")
@Import({MyBatisConfig.class,SpringMvcConfig.class,JDBCConfig.class})

public class SpringConfig {
}
