package com.jason.dao;

import java.util.ArrayList;
import java.util.List;

public class DeptAndEmp extends Dept {

    private List<EMP> empList;

    public List<EMP> getEmpList() {
        return empList;
    }

    public void setEmpList(List<EMP> empList) {
        this.empList = empList;
    }

    @Override
    public String toString() {
        return super.toString() + "DeptAndEmp{" +
                "empList=" + empList +
                '}' + "\n";
    }

}
