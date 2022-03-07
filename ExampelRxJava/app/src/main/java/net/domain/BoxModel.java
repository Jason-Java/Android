package net.domain;

public class BoxModel
{  private String BoxTypeId;
   private String Model;
   private String Name;
   private String Description;
   private String Rows;
   private String Columns;
   private String ContainerRows;
   private String ContainerColumns;
   private String Capacity;
   private String LockType;
   private String BoxType;
   private boolean Enabled;
   private String Id;

   private String format(String str)
   {
       return str == null ? "" : str;
   }

    public String getBoxTypeId()
    {
        return format(BoxTypeId);
    }

    public String getModel()
    {
        return format(Model);
    }

    public String getName()
    {
        return format(Name);
    }

    public String getDescription()
    {
        return format(Description);
    }

    public String getRows()
    {
        return format(Rows);
    }

    public String getColumns()
    {
        return format(Columns);
    }

    public String getContainerRows()
    {
        return format(ContainerRows);
    }

    public String getContainerColumns()
    {
        return format(ContainerColumns);
    }

    public String getCapacity()
    {
        return format(Capacity);
    }

    public String getLockType()
    {
        return format(LockType);
    }

    public String getBoxType()
    {
        return format(BoxType);
    }

    public boolean getEnabled()
    {
        return Enabled;
    }

    public String getId()
    {
        return format(Id);
    }

    public void setBoxTypeId(String boxTypeId)
    {
        BoxTypeId = boxTypeId;
    }

    public void setModel(String model)
    {
        Model = model;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public void setDescription(String description)
    {
        Description = description;
    }

    public void setRows(String rows)
    {
        Rows = rows;
    }

    public void setColumns(String columns)
    {
        Columns = columns;
    }

    public void setContainerRows(String containerRows)
    {
        ContainerRows = containerRows;
    }

    public void setContainerColumns(String containerColumns)
    {
        ContainerColumns = containerColumns;
    }

    public void setCapacity(String capacity)
    {
        Capacity = capacity;
    }

    public void setLockType(String lockType)
    {
        LockType = lockType;
    }

    public void setBoxType(String boxType)
    {
        BoxType = boxType;
    }

    public void setEnabled(boolean enabled)
    {
        Enabled = enabled;
    }

    public void setId(String id)
    {
        Id = id;
    }
}
