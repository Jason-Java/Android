package com.jason.system.model.service;

import com.jason.system.model.domain.SysOperLog;

public interface ISysOperLogService {


    /**
     * 写入日志
     * @param operLog
     * @return
     */
    public int insertOperlog(SysOperLog operLog);

}
