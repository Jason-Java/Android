package net.domain;

public class Instrument {
   private String  InstrumentCode;
   private String  Name;
   private String  AssetNumber;
   private String  FactoryNumber;
   private String  Model;
   private String  Brand;
   private String  Classification;
   private String  Amount;
   private String  StoragePlace;
   private String  Custodian;
   private String  Phone;
   private String  Purpose;
   private String  Remark1;
   private String  FileUrl;
   private String  Status;
   private String  SalesCompany;
   private String  Price;
   private String  PurchaseTime;
   private String  GenerationTime;
   private String  UseAge;
   private String  MaintenanceCompany;
   private String  Remark2;
   private String  InvoiceNumber;
   private String  InvoicePic;
   private String  AcceptanceReport;
   private String  QRcodePic;
    private String  InstrumentPic;
   private String  CompanyId;
   private boolean  IsDelete;
   private String  Admin;
   private String  AdminName;
   private String  AdminPhone;
   private String  VerifyType;
   private String  VerifyPeriod;
   private String  LastVerifyDate;
   private String  NextVerifyDate;
   private String  LastNotityDate;
   private String  CheckNotityDate;
   private String  RSId;
   private String  CreateId;
   private String  CreateBy;
   private String  CreateTime;
   private String  ModifyId;
   private String  ModifyBy;
   private String  ModifyTime;
   private String  Id;

    public String getInstrumentCode() {
        return InstrumentCode==null?"":InstrumentCode;
    }

    public String getName() {
        return Name==null?"":Name;
    }

    public String getAssetNumber() {
        return AssetNumber==null?"":AssetNumber;
    }

    public String getFactoryNumber() {
        return FactoryNumber==null?"":FactoryNumber;
    }

    public String getModel() {
        return Model==null?"":Model;
    }

    public String getBrand() {
        return Brand==null?"":Brand;
    }

    public String getClassification() {
        return Classification==null?"":Classification;
    }

    public String getAmount() {
        return Amount==null?"":Amount;
    }

    public String getStoragePlace() {
        return StoragePlace==null?"":StoragePlace;
    }

    public String getCustodian() {
        return Custodian==null?"":Custodian;
    }

    public String getPhone() {
        return Phone==null?"":Phone;
    }

    public String getPurpose() {
        return Purpose==null?"":Purpose;
    }

    public String getRemark1() {
        return Remark1==null?"":Remark1;
    }

    public String getFileUrl() {
        return FileUrl==null?"":FileUrl;
    }

    public String getStatus() {
        return Status==null?"":Status;
    }

    public String getSalesCompany() {
        return SalesCompany==null?"":SalesCompany;
    }

    public String getPrice() {
        return Price==null?"":Price;
    }

    public String getPurchaseTime() {
        return PurchaseTime==null?"":PurchaseTime.substring(0,10);
    }

    public String getGenerationTime() {
        return GenerationTime==null?"":GenerationTime.substring(0,10);
    }

    public String getUseAge() {
        return UseAge==null?"":UseAge;
    }

    public String getMaintenanceCompany() {
        return MaintenanceCompany==null?"":MaintenanceCompany;
    }

    public String getRemark2() {
        return Remark2==null?"":Remark2;
    }

    public String getInvoiceNumber() {
        return InvoiceNumber==null?"":InvoiceNumber;
    }

    public String getInvoicePic() {
        return InvoicePic==null?"":InvoicePic;
    }

    public String getAcceptanceReport() {
        return AcceptanceReport==null?"":AcceptanceReport;
    }

    public String getQRcodePic() {
        return QRcodePic==null?"":QRcodePic;
    }

    public String getInstrumentPic() {
        return InstrumentPic==null?"":InstrumentPic;
    }

    public String getCompanyId() {
        return CompanyId==null?"":CompanyId;
    }

    public boolean getIsDelete() {
        return IsDelete;
    }

    public String getAdmin() {
        return Admin==null?"":Admin;
    }

    public String getAdminName() {
        return AdminName==null?"":AdminName;
    }

    public String getAdminPhone() {
        return AdminPhone==null?"":AdminPhone;
    }

    public String getVerifyType() {
        return VerifyType==null?"":VerifyType;
    }

    public String getVerifyPeriod() {
        return VerifyPeriod==null?"":VerifyPeriod;
    }

    public String getLastVerifyDate() {
        return LastVerifyDate==null?"":LastVerifyDate.substring(0,10);
    }

    public String getNextVerifyDate() {
        return NextVerifyDate==null?"":NextVerifyDate.substring(0,10);
    }

    public String getLastNotityDate() {
        return LastNotityDate==null?"":LastNotityDate.substring(0,10);
    }

    public String getCheckNotityDate() {
        return CheckNotityDate==null?"":CheckNotityDate.substring(0,10);
    }

    public String getRSId() {
        return RSId==null?"":RSId;
    }

    public String getCreateId() {
        return CreateId==null?"":CreateId;
    }

    public String getCreateBy() {
        return CreateBy==null?"":CreateBy;
    }

    public String getCreateTime() {
        return CreateTime==null?"":CreateTime.substring(0,10);
    }

    public String getModifyId() {
        return ModifyId==null?"":ModifyId;
    }

    public String getModifyBy() {
        return ModifyBy==null?"":ModifyBy;
    }

    public String getModifyTime() {
        return ModifyTime==null?"":ModifyTime.substring(0,10);
    }

    public String getId() {
        return Id;
    }
}
