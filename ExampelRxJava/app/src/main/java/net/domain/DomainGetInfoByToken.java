package net.domain;

import java.util.List;

public class DomainGetInfoByToken
{
    private User user;
    private List<Company> company;
    private Role role;
    private String deptid;

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public List<Company> getCompany()
    {
        return company;
    }

    public void setCompany(List<Company> company)
    {
        this.company = company;
    }

    public Role getRole()
    {
        return role;
    }

    public void setRole(Role role)
    {
        this.role = role;
    }

    public String getDeptid()
    {
        return deptid;
    }

    public void setDeptid(String deptid)
    {
        this.deptid = deptid;
    }
}
