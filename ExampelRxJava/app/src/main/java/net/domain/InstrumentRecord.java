package net.domain;



import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class InstrumentRecord {
          private String   Code;
          private String   InstrumentCode;
          private String   Phone;
          private String   UserId;
          private String   UserName;
          private String   StartTime;
          private String   EndTime;
          private String   Tem;
          private String   Hum;
          private String   Purpose;
          private String   Remark;
          private String   Item;
          private String   SampleCode;
          private String   BeforeStatus;
          private String   AfterStatus;
          private String   CompanyId;
          private String   Id;

    public String getCode() {
        return Code==null?"":Code;
    }

    public String getInstrumentCode() {
        return InstrumentCode==null?"":InstrumentCode;
    }

    public String getPhone() {
        return Phone==null?"":Phone;
    }

    public String getUserId() {
        return UserId==null?"":UserId;
    }

    public String getUserName() {
        return UserName==null?"":UserName;
    }

    public String getStartTime() {
        return StartTime==null?"":StartTime;
    }

    public String getEndTime() {
        return EndTime==null?"":EndTime;
    }

    public String getTem() {
        return Tem==null?"":Tem;
    }

    public String getHum() {
        return Hum==null?"":Hum;
    }

    public String getPurpose() {
        return Purpose==null?"":Purpose;
    }

    public String getRemark() {
        return Remark==null?"":Remark;
    }

    public String getItem() {
        return Item==null?"":Item;
    }

    public String getSampleCode() {
        return SampleCode==null?"":SampleCode;
    }

    public String getBeforeStatus() {
        return BeforeStatus==null?"":BeforeStatus;
    }

    public String getAfterStatus() {
        return AfterStatus==null?"":AfterStatus;
    }

    public String getCompanyId() {
        return CompanyId==null?"":CompanyId;
    }

    public String getId() {
        return Id;
    }

    public void setCode(String code) {
        Code = code;
    }

    public void setInstrumentCode(String instrumentCode) {
        InstrumentCode = instrumentCode;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public void setTem(String tem) {
        Tem = tem;
    }

    public void setHum(String hum) {
        Hum = hum;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public void setItem(String item) {
        Item = item;
    }

    public void setSampleCode(String sampleCode) {
        SampleCode = sampleCode;
    }

    public void setBeforeStatus(String beforeStatus) {
        BeforeStatus = beforeStatus;
    }

    public void setAfterStatus(String afterStatus) {
        AfterStatus = afterStatus;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public void setId(String id) {
        Id = id;
    }


//    private ArrayList<InstrumentRecord> convertTo(ArrayList<MeterRecord> meterRecords)
//    {
//        ArrayList<InstrumentRecord> instrumentRecords = new ArrayList<>();
//
//        for (int i = 0; i < meterRecords.size(); i++) {
//            InstrumentRecord instrumentRecord = new InstrumentRecord();
//            instrumentRecord.setUserId(meterRecords.get(i).getUserId());
//            instrumentRecord.setUserName (meterRecords.get(i).getUserName());
//            instrumentRecord.setStartTime (meterRecords.get(i).getStartTime ());
//            instrumentRecord.setEndTime (meterRecords.get(i).getEndTime ());
//            instrumentRecord.setTem (meterRecords.get(i).getTem ());
//            instrumentRecord.setHum (meterRecords.get(i).getHum ());
//            instrumentRecord.setPurpose (meterRecords.get(i).getPurpose ());
//            instrumentRecord.setRemark (meterRecords.get(i).getRemark ());
//            instrumentRecord.setItem (meterRecords.get(i).getItem ());
//            instrumentRecord.setBeforeStatus (meterRecords.get(i).getBeforeStatus ());
//            instrumentRecord.setSampleCode (meterRecords.get(i).getSampleCode ());
//            instrumentRecord.setAfterStatus (meterRecords.get(i).getAfterStatus ());
//            instrumentRecord.setCompanyId (meterRecords.get(i).getCompanyId ());
//            instrumentRecord. (meterRecords.get(i).get ());
//        }
//
//    }
}
