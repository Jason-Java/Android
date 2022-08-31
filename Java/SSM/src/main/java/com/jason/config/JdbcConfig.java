package com.jason.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

public class JdbcConfig {

    @Value("${db.driver}")
    private String driver;
    @Value("${db.url}")
    private String url;
    @Value("${db.user}")
    private String userName;
    @Value("${db.password}")
    private String password;

    @Bean
    public DruidDataSource druidDataSource() {
        DruidDataSource dt = new DruidDataSource();
        dt.setDriverClassName(driver);
        dt.setUrl(url);
        dt.setUsername(userName);
        dt.setPassword(password);
        return dt;
    }


}
