package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @SpringBootApplication  标记成SpringBoot的启动类(SoringBoot 的入口) 一个工程中只能有一个
 * 启动入库需要放到项目根目录下面
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
