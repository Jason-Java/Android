package com.jason.usb;

public enum UsbPrintBrand {
    // 铭印打印机
    MING_YIN_PRINT("1305");
    private String code;

    private UsbPrintBrand(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
