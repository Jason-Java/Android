package com.jason.service.impl;

import com.jason.dao.mapper.IAccountMapper;
import com.jason.domain.Account;
import com.jason.service.IAccountService;
import org.apache.ibatis.session.SqlSession;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Lazy
public class AccountServiceImpl implements IAccountService, InitializingBean {


    @Autowired
    SqlSession sqlSession;

    private IAccountMapper iAccountMapper;

    public AccountServiceImpl() {
        System.out.println("user Bean");

    }


    private void ini() {

    }

    @Override
    public ArrayList<Account> selectAll() {

        return iAccountMapper.selectAll();
    }

    @Transactional
    public void transferCount(int id, int id2, int count) {
        iAccountMapper.outCount(id, count);
        sqlSession.commit();

        iAccountMapper.inCount(id2, count);
        sqlSession.commit();

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        iAccountMapper = sqlSession.getMapper(IAccountMapper.class);
    }
}
