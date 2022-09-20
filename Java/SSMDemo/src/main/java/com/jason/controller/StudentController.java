package com.jason.controller;

import com.jason.domain.Student;
import com.jason.service.StudentService;
import com.jason.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ArrayList<Student> selectAll() {
        return studentService.selectAll();
    }

    @GetMapping("/{no}")
    public Student selectOne(@PathVariable  Integer no) {
        return studentService.selectOne(no);
    }





}
