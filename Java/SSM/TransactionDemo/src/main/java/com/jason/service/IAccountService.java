package com.jason.service;

import com.jason.domain.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

public interface IAccountService {



    ArrayList<Account> selectAll();

    @Transactional
    void transferCount(int id, int id2, int count);


}
