package com.jason;

import com.jason.dao.ABC;
import com.jason.dao.DeptAndEmp;
import com.jason.dao.EMP;
import com.jason.dao.EmpAndDept;
import com.jason.mapper.EmpMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("myBatisConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmpMapper iempMapper= sqlSession.getMapper(EmpMapper.class);
        /*
        //多对一映射查询
        ArrayList<EmpAndDept> empArrayList = iempMapper.empAndDept();
        for (EmpAndDept emp:empArrayList)
        {
            System.out.println(emp.toString());

        }*/

       /* //一对多映射查询
        ArrayList<DeptAndEmp> deptAndEmps = iempMapper.deptAndEmpResultMap();
        for (DeptAndEmp deptAndEmp :
                deptAndEmps) {
            System.out.println(deptAndEmp.toString());
        }*/

        //7698 BLAKE
        ArrayList<EMP> empArrayList=iempMapper.selectOne(7521,new String[0]);
        for (EMP emp :
                empArrayList) {
            System.out.println(emp.toString());
        }
    }
}
