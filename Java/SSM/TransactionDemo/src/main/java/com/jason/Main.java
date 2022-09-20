package com.jason;

import com.jason.config.SpringConfig;
import com.jason.dao.mapper.IAccountMapper;
import com.jason.domain.Account;
import com.jason.service.impl.AccountServiceImpl;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);


//        SqlSessionFactory sqlSessionFactory = context.getBean(SqlSessionFactory.class);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        IAccountMapper accountService = sqlSession.getMapper(IAccountMapper.class);
//
        AccountServiceImpl accountService = context.getBean(AccountServiceImpl.class);
        accountService.transferCount(1, 2, 100);
        ArrayList<Account> arrayList = accountService.selectAll();
        for (Account account : arrayList) {
            System.out.println(account.toString());

        }
    }
}
