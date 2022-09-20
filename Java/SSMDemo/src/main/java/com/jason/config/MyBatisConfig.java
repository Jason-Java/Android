package com.jason.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

public class MyBatisConfig {


    @Bean
    public SqlSessionFactoryBean sessionFactoryBean(DataSource dataSource) {
        SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
        ssfb.setDataSource(dataSource);
        ssfb.setTypeAliasesPackage("com.jason.domain");
        //加载配置文件
        Resource[] mapperResources = new Resource[0];
        try {
            mapperResources = new PathMatchingResourcePatternResolver().getResources("classpath:*Mapper.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ssfb.setMapperLocations(mapperResources);
        return ssfb;
    }


    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.jason.mapper");
        return mapperScannerConfigurer;
    }


}
