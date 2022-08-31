package com.jason.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.jason")
@PropertySource("classpath:init.properties")
@Import({JdbcConfig.class,MyBatisConfig.class}) //加载另外一个配置文件
public class SpringConfig {
}
