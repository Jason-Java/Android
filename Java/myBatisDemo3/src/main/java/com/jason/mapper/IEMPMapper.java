package com.jason.mapper;

import com.jason.dao.EMP;
import com.jason.dao.IEmpAndDeptResult;

import java.util.ArrayList;

public interface IEMPMapper {
    ArrayList<EMP> selectAll();

    ArrayList<IEmpAndDeptResult> selectEmpAndDept();
}
