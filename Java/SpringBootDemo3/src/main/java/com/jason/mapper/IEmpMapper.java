package com.jason.mapper;

import com.jason.domain.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface IEmpMapper {

    ArrayList<Emp> selectAll();
}
