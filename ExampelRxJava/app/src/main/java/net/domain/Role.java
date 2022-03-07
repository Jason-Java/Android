package net.domain;

public class Role
{
    private String Guid;
    private boolean IsDeleted;
    private String Name;
    private String Description;
    private String OrderSort;
    private boolean Enabled;
    private String TargetTypes;
    private String CompanyId;
    private String CreateId;
    private String CreateBy;
    private String CreateTime;
    private String ModifyId;
    private String ModifyBy;
    private String ModifyTime;
    private String Id;

    private String format(String str)
    {
        return str == null ? "" : str;
    }

    public String getGuid()
    {
        return format(Guid);
    }

    public boolean getIsDeleted()
    {
        return IsDeleted;
    }

    public String getName()
    {
        return format(Name);
    }

    public String getDescription()
    {
        return format(Description);
    }

    public String getOrderSort()
    {
        return format(OrderSort);
    }

    public boolean getEnabled()
    {
        return Enabled;
    }

    public String getTargetTypes()
    {
        return format(TargetTypes);
    }

    public String getCompanyId()
    {
        return format(CompanyId);
    }

    public String getCreateId()
    {
        return format(CreateId);
    }

    public String getCreateBy()
    {
        return format(CreateBy);
    }

    public String getCreateTime()
    {
        return format(CreateTime);
    }

    public String getModifyId()
    {
        return format(ModifyId);
    }

    public String getModifyBy()
    {
        return format(ModifyBy);
    }

    public String getModifyTime()
    {
        return format(ModifyTime);
    }

    public String getId()
    {
        return format(Id);
    }

    public void setGuid(String guid)
    {
        Guid = guid;
    }

    public void setDeleted(boolean deleted)
    {
        IsDeleted = deleted;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public void setDescription(String description)
    {
        Description = description;
    }

    public void setOrderSort(String orderSort)
    {
        OrderSort = orderSort;
    }

    public void setEnabled(boolean enabled)
    {
        Enabled = enabled;
    }

    public void setTargetTypes(String targetTypes)
    {
        TargetTypes = targetTypes;
    }

    public void setCompanyId(String companyId)
    {
        CompanyId = companyId;
    }

    public void setCreateId(String createId)
    {
        CreateId = createId;
    }

    public void setCreateBy(String createBy)
    {
        CreateBy = createBy;
    }

    public void setCreateTime(String createTime)
    {
        CreateTime = createTime;
    }

    public void setModifyId(String modifyId)
    {
        ModifyId = modifyId;
    }

    public void setModifyBy(String modifyBy)
    {
        ModifyBy = modifyBy;
    }

    public void setModifyTime(String modifyTime)
    {
        ModifyTime = modifyTime;
    }

    public void setId(String id)
    {
        Id = id;
    }
}
