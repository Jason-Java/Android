package com.jason.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.Reader;

@Configuration
@ComponentScan("com.jason")
@PropertySource("classpath:init.properties")
@Import({MyBatisConfig.class})
@EnableTransactionManagement

public class SpringConfig {

    @Bean
    public SqlSession sqlSessionFactory() throws IOException {
        SqlSessionFactory sqlf = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("myBatisConfig.xml"));
        return sqlf.openSession();
    }

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

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dstm = new DataSourceTransactionManager();
        dstm.setDataSource(dataSource);
        return dstm;
    }


}
