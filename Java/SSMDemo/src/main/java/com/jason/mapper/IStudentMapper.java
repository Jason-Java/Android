package com.jason.mapper;

import com.jason.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface IStudentMapper {

    ArrayList<Student> selectAll();

    Student selectOne(@Param("no") Integer id);

    int insertOne(Student student);

    void deleteOne(@Param("no") int no,@Param("name") String name);

    void update(@Param("Student") Student student, @Param("no") int no);

}
