package com.jason.system.model.service;

public interface ISysConfigService {

    /**
     * 获取验证码开关
     *
     * @return true开启， false关闭
     */
    public boolean selectCaptchaEnable();
}
