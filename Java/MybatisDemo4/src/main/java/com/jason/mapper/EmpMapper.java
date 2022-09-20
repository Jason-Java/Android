package com.jason.mapper;

import com.jason.dao.ABC;
import com.jason.dao.DeptAndEmp;
import com.jason.dao.EMP;
import com.jason.dao.EmpAndDept;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.Map;

public interface EmpMapper {


    ArrayList<EMP> selectOne(@Param("id") Integer id, @Param("name") String [] names);


    ArrayList<EmpAndDept> empAndDept();

    ArrayList<DeptAndEmp> deptAndEmpResultMap();
}
