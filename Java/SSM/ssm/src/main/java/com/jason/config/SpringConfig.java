package com.jason.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.jason")
@Import({MyBatisConfig.class,SpringMvcConfig.class})
public class SpringConfig {
}
