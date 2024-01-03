package com.lotsrc.driver;

interface IRemoteRequest {
    void mainToService(String tempValue);
    void registerCallBack(com.lotsrc.driver.IRemoteResponse response);
    void unregisterCallBack();
}

