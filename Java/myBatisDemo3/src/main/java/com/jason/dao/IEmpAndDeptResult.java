package com.jason.dao;

public class IEmpAndDeptResult extends EMP {

    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return super.toString()+"IEmpAndDeptResult{" +
                "dept=" + dept +
                '}'+"\n";
    }
}
