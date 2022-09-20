package com.jason;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration  //标注此类是配置类
@ComponentScan("com.jason") //扫描包下面的Bean
@PropertySource({"init.properties"}) //读取资源文件
public class Application {


    //   注入资源通过属性字段进行注入
    @Value("${driver}")
    String driver;

    //注入引用类型 通过参数进行自动装配




    @Bean
    public DruidDataSource getDruidDataSource() {
        DruidDataSource dt = new DruidDataSource();
        dt.setDriverClassName("driver");
        dt.setUrl("jdbc:mysql://localhost/bjpowernode");
        dt.setUsername("root");
        dt.setPassword("123456");
        return new DruidDataSource();
    }

}
