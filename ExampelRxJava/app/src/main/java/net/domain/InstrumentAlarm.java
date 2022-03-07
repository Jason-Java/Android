package net.domain;

import java.util.ArrayList;
import java.util.List;

public class InstrumentAlarm {
    private String Code;
    private String Type;
    private String InstrumentId;
    private String UserId;
    private String UserName;
    private String Pic;
    private String Time;
    private String CompanyId;
    private String AssetNumber;
    private String Name;
    private String StoragePlace;
    private String Id;

    public String getCode() {
        return Code == null ? "" : Code;
    }

    public String getType() {
        return Type == null ? "" : Type;
    }

    public String getInstrumentId() {
        return InstrumentId == null ? "" : InstrumentId;
    }

    public String getUserId() {
        return UserId == null ? "" : UserId;
    }

    public String getUserName() {
        return UserName == null ? "" : UserName;
    }

    public String getPic() {
        return Pic == null ? "" : Pic;
    }

    public String getTime() {
        return Time == null ? "" : Time.substring(0,10);
    }

    public String getCompanyId() {
        return CompanyId == null ? "" : CompanyId;
    }

    public String getId() {
        return Id;
    }

    public String getAssetNumber() {
        return AssetNumber== null ? "":AssetNumber;
    }

    public String getName() {
        return Name== null ? "" :Name;
    }

    public String getStoragePlace() {
        return StoragePlace== null ? "" :StoragePlace;
    }

    public void setCode(String code) {
        Code = code;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setInstrumentId(String instrumentId) {
        InstrumentId = instrumentId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPic(String pic) {
        Pic = pic;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setAssetNumber(String assetNumber) {
        AssetNumber = assetNumber;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setStoragePlace(String storagePlace) {
        StoragePlace = storagePlace;
    }

    public class Data{
        private int Total;
        private List<InstrumentAlarm> Data;

        public int getTotal() {
            return Total;
        }

        public void setTotal(int total) {
            Total = total;
        }

        public List<InstrumentAlarm> getData() {
            return Data;
        }

        public void setData(List<InstrumentAlarm> data) {
            Data = data;
        }
    }
}
