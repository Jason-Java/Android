package com.jason.dao;

public class EmpAndDept extends EMP{
    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return super.toString()+ "EmpAndDept{" +
                "dept=" + dept +
                '}'+"\n";
    }
}
