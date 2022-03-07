package net.domain;

public class Box
{
    private String Guid;
    private String Name;
    private String Description;
    private String Pic;
    private String IsDeleted;
    private String BoxModuleId;
    private String BoxModule;
    private String Enabled;
    private String ComNum;
    private String BoardType;
    private String BoardNum;
    private String LockIP;
    private String CameraIP;
    private String DevIP;
    private String OptocouplerIP;
    private String VocIP;
    private String PrinterIP;
    private String CompanyId;
    private String HCMAddress;
    private String Rows;
    private String Columns;
    private String Tem;
    private String AlarmTem;
    private String RSAddress;
    private boolean IsShare;
    private String UseProcess;
    private String WeightAddress;
    private String Faceids;
    private boolean DoubleFace;
    private String containers;
    private String WhiteList;
    private String Deptname;
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

    public String getName()
    {
        return format(Name);
    }

    public String getDescription()
    {
        return format(Description);
    }

    public String getPic()
    {
        return format(Pic);
    }

    public String getIsDeleted()
    {
        return format(IsDeleted);
    }

    public String getBoxModuleId()
    {
        return format(BoxModuleId);
    }

    public String getBoxModule()
    {
        return format(BoxModule);
    }

    public String getEnabled()
    {
        return format(Enabled);
    }

    public String getComNum()
    {
        return format(ComNum);
    }

    public String getBoardType()
    {
        return format(BoardType);
    }

    public String getBoardNum()
    {
        return format(BoardNum);
    }

    public String getLockIP()
    {
        return format(LockIP);
    }

    public String getCameraIP()
    {
        return format(CameraIP);
    }

    public String getDevIP()
    {
        return format(DevIP);
    }

    public String getOptocouplerIP()
    {
        return format(OptocouplerIP);
    }

    public String getVocIP()
    {
        return format(VocIP);
    }

    public String getPrinterIP()
    {
        return format(PrinterIP);
    }

    public String getCompanyId()
    {
        return format(CompanyId);
    }

    public String getHCMAddress()
    {
        return format(HCMAddress);
    }

    public String getRows()
    {
        return format(Rows);
    }

    public String getColumns()
    {
        return format(Columns);
    }

    public String getTem()
    {
        return format(Tem);
    }

    public String getAlarmTem()
    {
        return format(AlarmTem);
    }

    public String getRSAddress()
    {
        return format(RSAddress);
    }

    public boolean getIsShare()
    {
        return IsShare;
    }

    public String getUseProcess()
    {
        return format(UseProcess);
    }

    public String getWeightAddress()
    {
        return format(WeightAddress);
    }

    public String getFaceids()
    {
        return format(Faceids);
    }

    public boolean getDoubleFace()
    {
        return DoubleFace;
    }

    public String getContainers()
    {
        return format(containers);
    }

    public String getWhiteList()
    {
        return format(WhiteList);
    }

    public String getDeptname()
    {
        return format(Deptname);
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

    public void setPic(String pic)
    {
        Pic = pic;
    }

    public void setIsDeleted(String isDeleted)
    {
        IsDeleted = isDeleted;
    }

    public void setBoxModuleId(String boxModuleId)
    {
        BoxModuleId = boxModuleId;
    }

    public void setBoxModule(String boxModule)
    {
        BoxModule = boxModule;
    }

    public void setEnabled(String enabled)
    {
        Enabled = enabled;
    }

    public void setComNum(String comNum)
    {
        ComNum = comNum;
    }

    public void setBoardType(String boardType)
    {
        BoardType = boardType;
    }

    public void setBoardNum(String boardNum)
    {
        BoardNum = boardNum;
    }

    public void setLockIP(String lockIP)
    {
        LockIP = lockIP;
    }

    public void setCameraIP(String cameraIP)
    {
        CameraIP = cameraIP;
    }

    public void setDevIP(String devIP)
    {
        DevIP = devIP;
    }

    public void setOptocouplerIP(String optocouplerIP)
    {
        OptocouplerIP = optocouplerIP;
    }

    public void setVocIP(String vocIP)
    {
        VocIP = vocIP;
    }

    public void setPrinterIP(String printerIP)
    {
        PrinterIP = printerIP;
    }

    public void setCompanyId(String companyId)
    {
        CompanyId = companyId;
    }

    public void setHCMAddress(String HCMAddress)
    {
        this.HCMAddress = HCMAddress;
    }

    public void setRows(String rows)
    {
        Rows = rows;
    }

    public void setColumns(String columns)
    {
        Columns = columns;
    }

    public void setTem(String tem)
    {
        Tem = tem;
    }

    public void setAlarmTem(String alarmTem)
    {
        AlarmTem = alarmTem;
    }

    public void setRSAddress(String RSAddress)
    {
        this.RSAddress = RSAddress;
    }

    public void setShare(boolean share)
    {
        IsShare = share;
    }

    public void setUseProcess(String useProcess)
    {
        UseProcess = useProcess;
    }

    public void setWeightAddress(String weightAddress)
    {
        WeightAddress = weightAddress;
    }

    public void setFaceids(String faceids)
    {
        Faceids = faceids;
    }

    public void setDoubleFace(boolean doubleFace)
    {
        DoubleFace = doubleFace;
    }

    public void setContainers(String containers)
    {
        this.containers = containers;
    }

    public void setWhiteList(String whiteList)
    {
        WhiteList = whiteList;
    }

    public void setDeptname(String deptname)
    {
        Deptname = deptname;
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
