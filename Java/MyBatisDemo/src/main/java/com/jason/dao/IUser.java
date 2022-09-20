package com.jason.dao;

import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

public interface IUser {
    /*
     接口绑定方式:方法名需要和usermapper.xml中的id相对应
     ArrayList<User> selectUser();
     */
    @Select("select * from user")
    ArrayList<User> selectUser();
}
