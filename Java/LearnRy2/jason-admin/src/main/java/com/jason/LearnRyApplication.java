package com.jason;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@MapperScan("com.jason.system.mapper")
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class LearnRyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnRyApplication.class, args);
    }
}
