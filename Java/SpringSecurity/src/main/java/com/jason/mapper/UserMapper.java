package com.jason.mapper;

import com.jason.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {

    User selectOne(@Param("name") String name);
}
