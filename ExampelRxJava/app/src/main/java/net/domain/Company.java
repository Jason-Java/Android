package net.domain;

import android.media.ExifInterface;

public class Company
{
    private String Name;
    private String Code;
    private String Key;
    private String WhiteList;
    private String Comment;
    private boolean IsDeleted;
    private boolean Enabled;
    private String TargetTypes;
    private String SampleKeywords;
    private String ReagentKeywords;
    private String NormalReagentKeywords;
    private String SampleReminderKeywords;
    private String ReagentReminderKeywords;
    private String ConsumableReminderKeywords;
    private String SampleReminderContent;
    private String ReagentReminderContent;
    private String ConsumableReminderContent;
    private String ConsumableKeywords;
    private String ChromatographicKeywords;
    private String ChromatographicReminderKeywords;
    private String ChromatographicReminderContent;
    private String ChromatographicReminder;
    private String ChromatographicPrintModel;
    private String ExpireTime;
    private String AmountReminder;
    private String ConsumableReminder;
    private String SampleReminder;
    private String ReagentReminder;
    private String SamplePrintModel;
    private String ReagentPrintModel;
    private String ConsumablePrintModel;
    private String Videourl;
    private String Fileurl;
    private String Fileupurl;
    private boolean UseVolumnActive;
    private String SampleRequired;
    private String ReagentRequired;
    private String ConsumablesRequired;
    private boolean Lending;
    private String CreateId;
    private String CreateBy;
    private String CreateTime;
    private String ModifyId;
    private String ModifyBy;
    private String ModifyTime;
    private String Id;

    public String getName()
    {
        return Name == null ? "" : Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getCode()
    {
        return Code == null ? "" : Code;
    }

    public void setCode(String code)
    {
        Code = code;
    }

    public String getKey()
    {
        return Key == null ? "" : Key;
    }

    public void setKey(String key)
    {
        Key = key;
    }

    public String getWhiteList()
    {
        return WhiteList == null ? "" : WhiteList;
    }

    public void setWhiteList(String whiteList)
    {
        WhiteList = whiteList;
    }

    public String getComment()
    {
        return Comment == null ? "" : Comment;
    }

    public void setComment(String comment)
    {
        Comment = comment;
    }

    public boolean getIsDeleted()
    {
        return IsDeleted;
    }

    public void setDeleted(boolean deleted)
    {
        IsDeleted = deleted;
    }

    public boolean getEnabled()
    {
        return Enabled;
    }

    public void setEnabled(boolean enabled)
    {
        Enabled = enabled;
    }

    public String getTargetTypes()
    {
        return TargetTypes == null ? "" : TargetTypes;
    }

    public void setTargetTypes(String targetTypes)
    {
        TargetTypes = targetTypes;
    }

    public String getSampleKeywords()
    {
        return SampleKeywords == null ? "" : SampleKeywords;
    }

    public void setSampleKeywords(String sampleKeywords)
    {
        SampleKeywords = sampleKeywords;
    }

    public String getReagentKeywords()
    {
        return ReagentKeywords == null ? "" : ReagentKeywords;
    }

    public void setReagentKeywords(String reagentKeywords)
    {
        ReagentKeywords = reagentKeywords;
    }

    public String getNormalReagentKeywords()
    {
        return NormalReagentKeywords == null ? "" : NormalReagentKeywords;
    }

    public void setNormalReagentKeywords(String normalReagentKeywords)
    {
        NormalReagentKeywords = normalReagentKeywords;
    }

    public String getSampleReminderKeywords()
    {
        return SampleReminderKeywords == null ? "" : SampleReminderKeywords;
    }

    public void setSampleReminderKeywords(String sampleReminderKeywords)
    {
        SampleReminderKeywords = sampleReminderKeywords;
    }

    public String getReagentReminderKeywords()
    {
        return ReagentReminderKeywords == null ? "" : ReagentReminderKeywords;
    }

    public void setReagentReminderKeywords(String reagentReminderKeywords)
    {
        ReagentReminderKeywords = reagentReminderKeywords;
    }

    public String getConsumableReminderKeywords()
    {
        return ConsumableReminderKeywords == null ? "" : ConsumableReminderKeywords;
    }

    public void setConsumableReminderKeywords(String consumableReminderKeywords)
    {
        ConsumableReminderKeywords = consumableReminderKeywords;
    }

    public String getSampleReminderContent()
    {
        return SampleReminderContent == null ? "" : SampleReminderContent;
    }

    public void setSampleReminderContent(String sampleReminderContent)
    {
        SampleReminderContent = sampleReminderContent;
    }

    public String getReagentReminderContent()
    {
        return ReagentReminderContent == null ? "" : ReagentReminderContent;
    }

    public void setReagentReminderContent(String reagentReminderContent)
    {
        ReagentReminderContent = reagentReminderContent;
    }

    public String getConsumableReminderContent()
    {
        return ConsumableReminderContent == null ? "" : ConsumableReminderContent;
    }

    public void setConsumableReminderContent(String consumableReminderContent)
    {
        ConsumableReminderContent = consumableReminderContent;
    }

    public String getConsumableKeywords()
    {
        return ConsumableKeywords == null ? "" : ConsumableKeywords;
    }

    public void setConsumableKeywords(String consumableKeywords)
    {
        ConsumableKeywords = consumableKeywords;
    }

    public String getChromatographicKeywords()
    {
        return ChromatographicKeywords == null ? "" : ChromatographicKeywords;
    }

    public void setChromatographicKeywords(String chromatographicKeywords)
    {
        ChromatographicKeywords = chromatographicKeywords;
    }

    public String getChromatographicReminderKeywords()
    {
        return ChromatographicReminderKeywords == null ? "" : ChromatographicReminderKeywords;
    }

    public void setChromatographicReminderKeywords(String chromatographicReminderKeywords)
    {
        ChromatographicReminderKeywords = chromatographicReminderKeywords;
    }

    public String getChromatographicReminderContent()
    {
        return ChromatographicReminderContent == null ? "" : ChromatographicReminderContent;
    }

    public void setChromatographicReminderContent(String chromatographicReminderContent)
    {
        ChromatographicReminderContent = chromatographicReminderContent;
    }

    public String getChromatographicReminder()
    {
        return ChromatographicReminder == null ? "" : ChromatographicReminder;
    }

    public void setChromatographicReminder(String chromatographicReminder)
    {
        ChromatographicReminder = chromatographicReminder;
    }

    public String getChromatographicPrintModel()
    {
        return ChromatographicPrintModel == null ? "" : ChromatographicPrintModel;
    }

    public void setChromatographicPrintModel(String chromatographicPrintModel)
    {
        ChromatographicPrintModel = chromatographicPrintModel;
    }

    public String getExpireTime()
    {
        return ExpireTime == null ? "" : ExpireTime;
    }

    public void setExpireTime(String expireTime)
    {
        ExpireTime = expireTime;
    }

    public String getAmountReminder()
    {
        return AmountReminder == null ? "" : AmountReminder;
    }

    public void setAmountReminder(String amountReminder)
    {
        AmountReminder = amountReminder;
    }

    public String getConsumableReminder()
    {
        return ConsumableReminder == null ? "" : ConsumableReminder;
    }

    public void setConsumableReminder(String consumableReminder)
    {
        ConsumableReminder = consumableReminder;
    }

    public String getSampleReminder()
    {
        return SampleReminder == null ? "" : SampleReminder;
    }

    public void setSampleReminder(String sampleReminder)
    {
        SampleReminder = sampleReminder;
    }

    public String getReagentReminder()
    {
        return ReagentReminder == null ? "" : ReagentReminder;
    }

    public void setReagentReminder(String reagentReminder)
    {
        ReagentReminder = reagentReminder;
    }

    public String getSamplePrintModel()
    {
        return SamplePrintModel == null ? "" : SamplePrintModel;
    }

    public void setSamplePrintModel(String samplePrintModel)
    {
        SamplePrintModel = samplePrintModel;
    }

    public String getReagentPrintModel()
    {
        return ReagentPrintModel == null ? "" : ReagentPrintModel;
    }

    public void setReagentPrintModel(String reagentPrintModel)
    {
        ReagentPrintModel = reagentPrintModel;
    }

    public String getConsumablePrintModel()
    {
        return ConsumablePrintModel == null ? "" : ConsumablePrintModel;
    }

    public void setConsumablePrintModel(String consumablePrintModel)
    {
        ConsumablePrintModel = consumablePrintModel;
    }

    public String getVideourl()
    {
        return Videourl == null ? "" : Videourl;
    }

    public void setVideourl(String videourl)
    {
        Videourl = videourl;
    }

    public String getFileurl()
    {
        return Fileurl == null ? "" : Fileurl;
    }

    public void setFileurl(String fileurl)
    {
        Fileurl = fileurl;
    }

    public String getFileupurl()
    {
        return Fileupurl == null ? "" : Fileupurl;
    }

    public void setFileupurl(String fileupurl)
    {
        Fileupurl = fileupurl;
    }

    public boolean isUseVolumnActive()
    {
        return UseVolumnActive;
    }

    public void setUseVolumnActive(boolean useVolumnActive)
    {
        UseVolumnActive = useVolumnActive;
    }

    public String getSampleRequired()
    {
        return SampleRequired == null ? "" : SampleRequired;
    }

    public void setSampleRequired(String sampleRequired)
    {
        SampleRequired = sampleRequired;
    }

    public String getReagentRequired()
    {
        return ReagentRequired == null ? "" : ReagentRequired;
    }

    public void setReagentRequired(String reagentRequired)
    {
        ReagentRequired = reagentRequired;
    }

    public String getConsumablesRequired()
    {
        return ConsumablesRequired == null ? "" : ConsumablesRequired;
    }

    public void setConsumablesRequired(String consumablesRequired)
    {
        ConsumablesRequired = consumablesRequired;
    }

    public boolean getLending()
    {
        return Lending;
    }

    public void setLending(boolean lending)
    {
        Lending = lending;
    }

    public String getCreateId()
    {
        return CreateId == null ? "" : CreateId;
    }

    public void setCreateId(String createId)
    {
        CreateId = createId;
    }

    public String getCreateBy()
    {
        return CreateBy == null ? "" : CreateBy;
    }

    public void setCreateBy(String createBy)
    {
        CreateBy = createBy;
    }

    public String getCreateTime()
    {
        return CreateTime == null ? "" : CreateTime;
    }

    public void setCreateTime(String createTime)
    {
        CreateTime = createTime;
    }

    public String getModifyId()
    {
        return ModifyId == null ? "" : ModifyId;
    }

    public void setModifyId(String modifyId)
    {
        ModifyId = modifyId;
    }

    public String getModifyBy()
    {
        return ModifyBy == null ? "" : ModifyBy;
    }

    public void setModifyBy(String modifyBy)
    {
        ModifyBy = modifyBy;
    }

    public String getModifyTime()
    {
        return format(ModifyTime);
    }

    public void setModifyTime(String modifyTime)
    {
        ModifyTime = modifyTime;
    }

    public String getId()
    {
        return format(Id);
    }

    public void setId(String id)
    {
        Id = id;
    }

    private String format(String str)
    {
        return str == null ? "" : str;
    }
}
