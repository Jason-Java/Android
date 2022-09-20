package com.jason.mapper;

import com.jason.dao.EMP;
import com.jason.dao.Student;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface IStudentMapper {

    ArrayList<Student> selectAll(@Param("array") List<String> name);

    int insertOne(Student student);

    void deleteOne(int no);

//    void update(@Param("name") String name, @Param("no") int no);
    void update(@Param("Student") Student student, @Param("no") int no);

}
