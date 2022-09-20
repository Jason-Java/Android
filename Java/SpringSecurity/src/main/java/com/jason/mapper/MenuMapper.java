package com.jason.mapper;

import com.jason.domain.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> selectByUserName(@Param("userName") String userName);
}
