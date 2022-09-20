package com.jason.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"com.jason.service","com.jason.dao"})
public class SpringConfig {
}
