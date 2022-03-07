package net.domain;

import android.view.Display;

import java.io.Serializable;

public class Reagent implements Serializable {
    private String Alias;//别名
    private int Amount;
    private String ArtNo;
    private String Barcode;
    private String BoxId;
    private String BoxName;//: null
    private String Brand;
    private String CAS;
    private String CertificateNo;
    private String Characters;
    private String Chemical;
    private String Classification;
    private String Comment;
    private String CommonName;
    private String CompanyId;
    private String Consistency;
    private String ContainerId;
    private String ContainerName;//: null
    private String CreateBy;
    private String CreateId;
    private String CreateTime;
    private String DangerClasses;
    private String Density;//: null
    private String EnglishName;//: null
    private String ExpiryTime;//: null

    private String SGXY;//: null
    private String AQCC;//: null
    private String FQCZ;//: null
    private String FYCS;//: null
    private String Files;//: null
    private String Id;
    private boolean IsDelete;
    private boolean IsOpen;
    private String Label;
    private String Method;//: null
    private String Model;
    private String ModelId;
    private String ModifyBy;
    private String ModifyId;
    private String ModifyTime;
    private String Position;//: null
    private String Price;
    private String ProductName;
    private String RFID;//: null
    private String SlotPositionX;
    private String SlotPositionY;
    private String Status;
    private String StorageCondition;//: null
    private String StorageTime;
    private String StoreVideo;//: null
    private String Uncertainty;
    private String Vender;
    private boolean Volatilize;
    private String Volumn;
    private String WHXZ;//: null
    private String Weight;//: null
    private String Count;
    private String RecordId;
    private String UseVolumn;
    private String RestVolumn;

    private String Box;
    private boolean IsMyStorage;
    private String Record;


    public String getAQCC() {
        return AQCC == null ? "暂无相关资料" : AQCC;
    }

    public void setAQCC(String AQCC) {
        this.AQCC = AQCC;
    }

    public String getAlias() {
        return Alias == null ? "" : Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public int getAmount() {
        return Amount;
    }

    public void setAmount(int amount) {
        Amount = amount;
    }

    public String getArtNo() {
        return ArtNo == null ? "" : ArtNo;
    }

    public void setArtNo(String artNo) {
        ArtNo = artNo;
    }

    public String getBarcode() {
        return Barcode == null ? "" : Barcode;
    }

    public void setBarcode(String barcode) {
        Barcode = barcode;
    }

    public String getBoxId() {
        return BoxId == null ? "" : BoxId;
    }

    public void setBoxId(String boxId) {
        BoxId = boxId;
    }

    public String getBoxName() {
        return BoxName == null ? "" : BoxName;
    }

    public void setBoxName(String boxName) {
        BoxName = boxName;
    }

    public String getBrand() {
        return Brand == null ? "" : Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getCAS() {
        return CAS == null ? "" : CAS;
    }

    public void setCAS(String CAS) {
        this.CAS = CAS;
    }

    public String getCertificateNo() {
        return CertificateNo == null ? "" : CertificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        CertificateNo = certificateNo;
    }

    public String getCharacters() {
        return Characters == null ? "" : Characters;
    }

    public void setCharacters(String characters) {
        Characters = characters;
    }

    public String getChemical() {
        return Chemical == null ? "" : Chemical;
    }

    public void setChemical(String chemical) {
        Chemical = chemical;
    }

    public String getClassification() {
        return Classification == null ? "" : Classification;
    }

    public void setClassification(String classification) {
        Classification = classification;
    }

    public String getComment() {
        return Comment == null ? "" : Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getCommonName() {
        return CommonName == null ? "" : CommonName;
    }

    public void setCommonName(String commonName) {
        CommonName = commonName;
    }

    public String getCompanyId() {
        return CompanyId == null ? "" : CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public String getConsistency() {
        return Consistency == null ? "" : Consistency;
    }

    public void setConsistency(String consistency) {
        Consistency = consistency;
    }

    public String getContainerId() {
        return ContainerId == null ? "" : ContainerId;
    }

    public void setContainerId(String containerId) {
        ContainerId = containerId;
    }

    public String getContainerName() {
        return ContainerName == null ? "" : ContainerName;
    }

    public void setContainerName(String containerName) {
        ContainerName = containerName;
    }

    public String getCreateBy() {
        return CreateBy == null ? "" : CreateBy;
    }

    public void setCreateBy(String createBy) {
        CreateBy = createBy;
    }

    public String getCreateId() {
        return CreateId == null ? "" : CreateId;
    }

    public void setCreateId(String createId) {
        CreateId = createId;
    }

    public String getCreateTime() {
        return CreateTime == null ? "" : CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getDangerClasses() {
        return DangerClasses == null ? "" : DangerClasses;
    }

    public void setDangerClasses(String dangerClasses) {
        DangerClasses = dangerClasses;
    }

    public String getDensity() {
        return Density == null ? "" : Density;
    }

    public void setDensity(String density) {
        Density = density;
    }

    public String getEnglishName() {
        return EnglishName == null ? "" : EnglishName;
    }

    public void setEnglishName(String englishName) {
        EnglishName = englishName;
    }

    public String getExpiryTime() {
        return ExpiryTime == null ? "" : ExpiryTime;
    }

    public void setExpiryTime(String expiryTime) {
        ExpiryTime = expiryTime;
    }

    public String getFQCZ() {
        return FQCZ == null ? "暂无相关资料" : FQCZ;
    }

    public void setFQCZ(String FQCZ) {
        this.FQCZ = FQCZ;
    }

    public String getFYCS() {
        return FYCS == null ? "暂无相关资料" : FYCS;
    }

    public void setFYCS(String FYCS) {
        this.FYCS = FYCS;
    }

    public String getFiles() {
        return Files == null ? "" : Files;
    }

    public void setFiles(String files) {
        Files = files;
    }

    public String getId() {
        return Id == null ? "" : Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public boolean getIsDelete() {
        return IsDelete;
    }

    public void setDelete(boolean delete) {
        IsDelete = delete;
    }

    public boolean getIsOpen() {
        return IsOpen;
    }

    public void setOpen(boolean open) {
        IsOpen = open;
    }

    public String getLabel() {
        return Label == null ? "" : Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getMethod() {
        return Method == null ? "" : Method;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public String getModel() {
        return Model == null ? "" : Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getModelId() {
        return ModelId == null ? "" : ModelId;
    }

    public void setModelId(String modelId) {
        ModelId = modelId;
    }

    public String getModifyBy() {
        return ModifyBy == null ? "" : ModifyBy;
    }

    public void setModifyBy(String modifyBy) {
        ModifyBy = modifyBy;
    }

    public String getModifyId() {
        return ModifyId == null ? "" : ModifyId;
    }

    public void setModifyId(String modifyId) {
        ModifyId = modifyId;
    }

    public String getModifyTime() {
        return ModifyTime == null ? "" : ModifyTime;
    }

    public void setModifyTime(String modifyTime) {
        ModifyTime = modifyTime;
    }

    public String getPosition() {
        return Position == null ? "" : Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getPrice() {
        return Price == null ? "" : Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getProductName() {
        return ProductName == null ? "" : ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getRFID() {
        return RFID == null ? "" : RFID;
    }

    public void setRFID(String RFID) {
        this.RFID = RFID;
    }

    public String getSGXY() {
        return SGXY == null ? "暂无相关资料" : SGXY;
    }

    public void setSGXY(String SGXY) {
        this.SGXY = SGXY;
    }

    public String getSlotPositionX() {
        return SlotPositionX == null ? "" : SlotPositionX;
    }

    public void setSlotPositionX(String slotPositionX) {
        SlotPositionX = slotPositionX;
    }

    public String getSlotPositionY() {
        return SlotPositionY == null ? "" : SlotPositionY;
    }

    public void setSlotPositionY(String slotPositionY) {
        SlotPositionY = slotPositionY;
    }

    public String getStatus() {
        return Status == null ? "" : Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getStorageCondition() {
        return StorageCondition == null ? "" : StorageCondition;
    }

    public void setStorageCondition(String storageCondition) {
        StorageCondition = storageCondition;
    }

    public String getStorageTime() {
        return StorageTime == null ? "" : StorageTime;
    }

    public void setStorageTime(String storageTime) {
        StorageTime = storageTime;
    }

    public String getStoreVideo() {
        return StoreVideo == null ? "" : StoreVideo;
    }

    public void setStoreVideo(String storeVideo) {
        StoreVideo = storeVideo;
    }

    public String getUncertainty() {
        return Uncertainty == null ? "" : Uncertainty;
    }

    public void setUncertainty(String uncertainty) {
        Uncertainty = uncertainty;
    }

    public String getVender() {
        return Vender == null ? "" : Vender;
    }

    public void setVender(String vender) {
        Vender = vender;
    }

    public boolean getVolatilize() {
        return Volatilize;
    }

    public void setVolatilize(boolean volatilize) {
        Volatilize = volatilize;
    }

    public String getVolumn() {
        return Volumn == null ? "" : Volumn;
    }

    public void setVolumn(String volumn) {
        Volumn = volumn;
    }

    public String getWHXZ() {
        return WHXZ == null ? "暂无相关资料" : WHXZ;
    }

    public void setWHXZ(String WHXZ) {
        this.WHXZ = WHXZ;
    }

    public String getWeight() {
        return Weight == null ? "" : Weight;
    }

    public void setWeight(String weight) {
        Weight = weight;
    }

    public String getCount() {
        return Count == null ? "" : Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getRecordId() {
        return RecordId == null ? "" : RecordId;
    }

    public void setRecordId(String recordId) {
        RecordId = recordId;
    }

    public String getUseVolumn() {
        return UseVolumn == null ? "" : UseVolumn;
    }

    public void setUseVolumn(String useVolumn) {
        UseVolumn = useVolumn;
    }

    public String getRestVolumn() {
        return RestVolumn == null ? "" : RestVolumn;
    }

    public void setRestVolumn(String restVolumn) {
        RestVolumn = restVolumn;
    }

    public String getBox() {
        return Box==null?"":Box;
    }

    public void setBox(String box) {
        Box = box;
    }

    public boolean isMyStorage() {
        return IsMyStorage;
    }

    public void setMyStorage(boolean myStorage) {
        IsMyStorage = myStorage;
    }

    public String getRecord() {
        return Record == null ? "" : Record;
    }

    public void setRecord(String record) {
        Record = record;
    }
}
