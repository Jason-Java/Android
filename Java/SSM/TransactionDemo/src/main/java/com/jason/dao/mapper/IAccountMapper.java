package com.jason.dao.mapper;

import com.jason.domain.Account;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface IAccountMapper {

    ArrayList<Account> selectAll();

    void outCount(@Param("id") int id, @Param("count") int count);

    void inCount(@Param("id") int id, @Param("count") int count);

}
