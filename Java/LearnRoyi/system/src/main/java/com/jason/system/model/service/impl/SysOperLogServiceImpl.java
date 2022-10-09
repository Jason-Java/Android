package com.jason.system.model.service.impl;

import com.jason.system.model.domain.SysOperLog;
import com.jason.system.model.mapper.SysOperLogMapper;
import com.jason.system.model.service.ISysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>描述:
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/9 21:51
 * @see
 */
@Service
public class SysOperLogServiceImpl implements ISysOperLogService {

    @Autowired()
    private SysOperLogMapper mapper;
    /**
     * 写入日志
     *
     * @param operLog
     * @return
     */
    @Override
    public int insertOperlog(SysOperLog operLog) {

        return mapper.insertOperlog(operLog);
    }
}
