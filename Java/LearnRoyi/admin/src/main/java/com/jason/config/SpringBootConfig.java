package com.jason.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@ComponentScan("com.jason")
@Configuration
@MapperScan("com.jason")
@EnableWebSecurity
public class SpringBootConfig extends WebMvcConfigurationSupport {

}
