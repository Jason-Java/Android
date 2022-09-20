package com.jason.service.impl;

import com.jason.domain.Student;
import com.jason.mapper.IStudentMapper;
import com.jason.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
@Service
public class StudentServiceImpl implements StudentService {


    @Autowired
    IStudentMapper studentMapper;

    @Override
    public ArrayList<Student> selectAll() {
        return studentMapper.selectAll();
    }

    @Override
    public Student selectOne(Integer id) {
        return studentMapper.selectOne(id);
    }

    @Override
    public boolean save(Student student) {
        return false;
    }

    @Override
    public boolean update(Student student,Integer no) {
        return false;
    }

    @Override
    public boolean delete(Integer id, String name) {
        return false;
    }
}
