package com.jason.system.model.service;

import com.jason.system.model.domain.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>描述:用户 业务层
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/9/18 22:50
 * @see
 */
public interface ISysUserService {

    /**
     * 根据条件查询用户
     * @param sysUser
     * @return
     */
    List<SysUser> selectUserList(SysUser sysUser);


    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser selectUserByUserName(String username);

    /**
     * 校验用户是否有数据权限
     * @param userId
     */
    void checkUserDataScope(Long userId);
}
