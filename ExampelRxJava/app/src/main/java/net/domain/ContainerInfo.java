package net.domain;

public class ContainerInfo
{
    private Container Container;
    private int TotalHole;
    private int UsedHole;
    private int ReceivedNum;
    private int ExpiriedNum;
    private int ExpirieSoon;
    private int Capacity;
    private boolean CanOpen;

    private String format(String str)
    {
        return str == null ? "" : str;
    }

    public  Container getContainer()
    {
        return Container;
    }

    public int getTotalHole()
    {
        return TotalHole;
    }

    public int getUsedHole()
    {
        return UsedHole;
    }

    public int getReceivedNum()
    {
        return ReceivedNum;
    }

    public int getExpiriedNum()
    {
        return ExpiriedNum;
    }

    public int getExpirieSoon()
    {
        return ExpirieSoon;
    }

    public int getCapacity()
    {
        return Capacity;
    }



    public boolean getCanOpen()
    {
        return CanOpen;
    }

    public void setContainer(  Container container)
    {
        Container = container;
    }

    public void setTotalHole(int totalHole)
    {
        TotalHole = totalHole;
    }

    public void setUsedHole(int usedHole)
    {
        UsedHole = usedHole;
    }

    public void setReceivedNum(int receivedNum)
    {
        ReceivedNum = receivedNum;
    }

    public void setExpiriedNum(int expiriedNum)
    {
        ExpiriedNum = expiriedNum;
    }

    public void setExpirieSoon(int expirieSoon)
    {
        ExpirieSoon = expirieSoon;
    }

    public void setCanOpen(boolean canOpen)
    {
        CanOpen = canOpen;
    }
}

class Container{
  private String  Guid;
  private String  Name;
  private String  Description;
  private String  IsDeleted;
  private String  Rows;
  private String  Columns;
  private String  RowIndex;
  private String  ColumnIndex;
  private String  BoxId;
  private String  Model;
  private String  ModelImg;
  private String  Capacity;
  private String  LockNum;
  private String  KeyBoxCode;
  private String  CreateId;
  private String  CreateBy;
  private String  CreateTime;
  private String  ModifyId;
  private String  ModifyBy;
  private String  ModifyTime;
  private String  Id;

  private String format(String str)
  {
      return str == null ? "" : str;
  }

   public String getGuid()
   {
       return format(Guid);
   }

   public String getName()
   {
       return format(Name);
   }

   public String getDescription()
   {
       return format(Description);
   }

   public String getIsDeleted()
   {
       return format(IsDeleted);
   }

   public String getRows()
   {
       return format(Rows);
   }

   public String getColumns()
   {
       return format(Columns);
   }

   public String getRowIndex()
   {
       return format(RowIndex);
   }

   public String getColumnIndex()
   {
       return format(ColumnIndex);
   }

   public String getBoxId()
   {
       return format(BoxId);
   }

   public String getModel()
   {
       return format(Model);
   }

   public String getModelImg()
   {
       return format(ModelImg);
   }

   public String getCapacity()
   {
       return format(Capacity);
   }

   public String getLockNum()
   {
       return format(LockNum);
   }

   public String getKeyBoxCode()
   {
       return format(KeyBoxCode);
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

   public void setName(String name)
   {
       Name = name;
   }

   public void setDescription(String description)
   {
       Description = description;
   }

   public void setIsDeleted(String isDeleted)
   {
       IsDeleted = isDeleted;
   }

   public void setRows(String rows)
   {
       Rows = rows;
   }

   public void setColumns(String columns)
   {
       Columns = columns;
   }

   public void setRowIndex(String rowIndex)
   {
       RowIndex = rowIndex;
   }

   public void setColumnIndex(String columnIndex)
   {
       ColumnIndex = columnIndex;
   }

   public void setBoxId(String boxId)
   {
       BoxId = boxId;
   }

   public void setModel(String model)
   {
       Model = model;
   }

   public void setModelImg(String modelImg)
   {
       ModelImg = modelImg;
   }

   public void setCapacity(String capacity)
   {
       Capacity = capacity;
   }

   public void setLockNum(String lockNum)
   {
       LockNum = lockNum;
   }

   public void setKeyBoxCode(String keyBoxCode)
   {
       KeyBoxCode = keyBoxCode;
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
