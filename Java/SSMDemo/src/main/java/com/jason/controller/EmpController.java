package com.jason.controller;

import com.jason.domain.EMP;
import com.jason.mapper.IDeptMapper;
import com.jason.mapper.IEMPMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/emps")
public class EmpController {


    @Autowired
    IEMPMapper iempMapper;

    @GetMapping
    public ArrayList<EMP> getAll() {
        return iempMapper.selectAll();
    }
}
