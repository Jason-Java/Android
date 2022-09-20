package com.jason.system.model.service.impl;

import com.jason.system.model.domain.SysConfig;
import com.jason.system.model.mapper.SysConfigMapper;
import com.jason.system.model.service.ISysConfigService;
import com.jason.system.text.Convert;
import com.jason.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysConfigServiceImpl implements ISysConfigService {

    @Autowired
    private SysConfigMapper configMapper;
    /**
     * 根据键名查询配置信息
     * @param configKey 参数Key
     * @return 参数Value
     */
    public String  selectConfigByKey(String configKey)
    {
        // TODO: 2022/9/18 redis 读取缓存

        SysConfig config = new SysConfig();
        config.setConfigKey(configKey);
        SysConfig retConfig=configMapper.selectConfig(config);
        if(StringUtils.isNotNull(retConfig))
        {
            // TODO: 2022/9/18 redis 存储缓存
            return retConfig.getConfigValue();
        }
        return StringUtils.EMPTY;

    }


    /**
     * 获取验证码开关
     *
     * @return true开启， false关闭
     */
    @Override
    public boolean selectCaptchaEnable() {
        String captchaEnabled = selectConfigByKey("sys.account.captchaEnabled");
        if(StringUtils.isEmpty(captchaEnabled))
        {
            return true;
        }
        return Convert.toBool(captchaEnabled);
    }
}
