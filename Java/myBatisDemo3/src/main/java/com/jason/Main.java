package com.jason;

import com.jason.dao.EMP;
import com.jason.dao.IEmpAndDeptResult;
import com.jason.dao.Student;
import com.jason.mapper.IEMPMapper;
import com.jason.mapper.IStudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.print.attribute.standard.MediaSize;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {

        Reader reader = Resources.getResourceAsReader("myBatisConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
       IEMPMapper iempMapper= sqlSession.getMapper(IEMPMapper.class);
        ArrayList<IEmpAndDeptResult> empArrayList = iempMapper.selectEmpAndDept();
        for (IEmpAndDeptResult emp:empArrayList)
        {
            System.out.println(emp.toString());

        }
       /* IStudentMapper iStudentMapper = sqlSession.getMapper(IStudentMapper.class);
        ArrayList<Student> empArrayList = iStudentMapper.selectAll(Arrays.asList("王六六","李四"));
        for (Student student : empArrayList) {
            System.out.println(student.toString());
        }*/
        /*Student student = new Student();
        student.setName("炸六");
        student.setAge(13);
        student.setSex("女");
        student.setEmial("456@qq.com");

      int count=  iStudentMapper.insertOne(student);
        System.out.println("插入的数据数量 " + count);
        System.out.println("User prim key " + student.getNo());
        sqlSession.commit();*/


        /*empArrayList = iStudentMapper.selectAll();
        for (Student student1 : empArrayList) {
            System.out.println(student1.toString());
        }
        iStudentMapper.deleteOne(4);
        sqlSession.commit();
        empArrayList = iStudentMapper.selectAll();
        for (Student student1 : empArrayList) {
            System.out.println(student1.toString());
        }

        */
       /* Student student5 = new Student();
        student5.setName("王六六");
        iStudentMapper.update(student5,2);
        sqlSession.commit();
        empArrayList = iStudentMapper.selectAll();
        for (Student student1 : empArrayList) {
            System.out.println(student1.toString());
        }*/


    }
}
