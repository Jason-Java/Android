package com.jason;

import com.jason.config.SpringConfig;
import com.jason.dao.Dept;
import com.jason.dao.mapper.IDeptMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        SqlSessionFactory sqlSessionFactory = context.getBean(SqlSessionFactory.class);


        /*Reader reader = Resources.getResourceAsReader("myBatisConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);*/
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IDeptMapper deptMapper = sqlSession.getMapper(IDeptMapper.class);
        ArrayList<Dept> deptArray = deptMapper.selectAll();
        for (Dept dept : deptArray
        ) {
            System.out.println(dept.toString());
        }

        sqlSession.close();
    }
}
