package com.jason.service;

import com.jason.domain.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


public interface StudentService {


    ArrayList<Student> selectAll();

    Student selectOne(Integer id);

     boolean save(Student student);

    boolean update(Student student,Integer no);

    boolean delete(Integer id, String name);
}
