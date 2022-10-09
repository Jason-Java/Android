package com.jason.system.model.mapper;

import com.jason.system.model.domain.SysOperLog;
import org.mybatis.spring.annotation.MapperScan;

/**
 * <p>描述:日志
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/9 21:42
 * @see
 */
@MapperScan
public interface SysOperLogMapper {

    /**
     * 写入日志
     * @param operLog
     * @return
     */
    public int insertOperlog(SysOperLog operLog);
}
