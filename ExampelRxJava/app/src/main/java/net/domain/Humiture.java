package net.domain;

import  util.StringUtil;

//温湿度
public class Humiture {
    private String BoxId;
    private String DeviceId;
    private String Tem;
    private String Hum;
    private String DataTime;
    private String Id;


    public String getBoxId() {
        return StringUtil.isEmpty(BoxId)?"":BoxId;
    }

    public String getDeviceId() {
        return StringUtil.isEmpty(DeviceId)?"":DeviceId;
    }

    public String getTem() {
        return  StringUtil.isEmpty(Tem)?"":Tem;
    }

    public String getHum() {
        return  StringUtil.isEmpty(Hum)?"":Hum;
    }

    public String getDataTime() {
        return  StringUtil.isEmpty(DataTime)?"":DataTime;
    }

    public String getId() {
        return Id;
    }

    public void setBoxId(String boxId) {
        BoxId = boxId;
    }

    public void setDeviceId(String deviceId) {
        DeviceId = deviceId;
    }

    public void setTem(String tem) {
        Tem = tem;
    }

    public void setHum(String hum) {
        Hum = hum;
    }

    public void setDataTime(String dataTime) {
        DataTime = dataTime;
    }

    public void setId(String id) {
        Id = id;
    }
}
