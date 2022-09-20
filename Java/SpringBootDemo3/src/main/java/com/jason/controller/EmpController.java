package com.jason.controller;

import com.jason.domain.Emp;
import com.jason.mapper.IEmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private IEmpMapper empMapper;

    @GetMapping
    public ArrayList<Emp> selectAll() {
        return empMapper.selectAll();
    }
}
