package com.unite.customepack;

import java.util.Arrays;

public class WebSocketMessage {
    private String DeviceCode;
    private int Drt;
    private boolean Success;
    private Result Result;

    public String getDeviceCode() {
        return DeviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        DeviceCode = deviceCode;
    }

    public int getDrt() {
        return Drt;
    }

    public void setDrt(int drt) {
        Drt = drt;
    }

    public boolean getSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public WebSocketMessage.Result getResult() {
        return Result;
    }

    public void setResult(WebSocketMessage.Result result) {
        Result = result;
    }

    @Override
    public String toString() {
        return "WebSocketMessage{" +
                "DeviceCode='" + DeviceCode + '\'' +
                ", Drt=" + Drt +
                ", Success=" + Success +
                ", Result=" + Result +
                '}';
    }

    public class Result {
        private String devicecode;
        private Integer ResultCode;
        private Integer[] Dots;
        private boolean Success;
        private Integer state;
        private Integer lockstatus;
        private String recordtime;
        private String battery;
        private String temperature;
        private String humidity;


        public String getDeviceCode() {
            return devicecode;
        }

        public void setDeviceCode(String deviceCode) {
            devicecode = deviceCode;
        }

        public Integer getResultCode() {
            return ResultCode;
        }

        public void setResultCode(Integer resultCode) {
            ResultCode = resultCode;
        }

        public Integer[] getDots() {
            return Dots;
        }

        public void setDots(Integer[] dots) {
            Dots = dots;
        }

        public boolean getSuccess() {
            return Success;
        }

        public void setSuccess(boolean success) {
            Success = success;
        }

        public String getDevicecode() {
            return devicecode;
        }

        public void setDevicecode(String devicecode) {
            this.devicecode = devicecode;
        }

        public boolean isSuccess() {
            return Success;
        }


        public Integer getState() {
            return state;
        }

        public void setState(Integer state) {
            this.state = state;
        }

        public Integer getLockstatus() {
            return lockstatus;
        }

        public void setLockstatus(Integer lockstatus) {
            this.lockstatus = lockstatus;
        }

        public String getRecordtime() {
            return recordtime;
        }

        public void setRecordtime(String recordtime) {
            this.recordtime = recordtime;
        }

        public String getBattery() {
            return battery;
        }

        public void setBattery(String battery) {
            this.battery = battery;
        }

        public String getTemperature() {
            return temperature;
        }

        public void setTemperature(String temperature) {
            this.temperature = temperature;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "devicecode='" + devicecode + '\'' +
                    ", ResultCode=" + ResultCode +
                    ", Dots=" + Arrays.toString(Dots) +
                    ", Success=" + Success +
                    ", state=" + state +
                    ", lockstatus=" + lockstatus +
                    ", recordtime='" + recordtime + '\'' +
                    ", battery='" + battery + '\'' +
                    ", temperature='" + temperature + '\'' +
                    ", humidity='" + humidity + '\'' +
                    '}';
        }
    }
}
