package com.jason.system.model.service.impl;

import com.jason.ApplicationContext;
import com.jason.system.annotation.DataScope;
import com.jason.system.annotation.Log;
import com.jason.system.model.domain.SysMenu;
import com.jason.system.model.domain.SysRole;
import com.jason.system.model.domain.SysUser;
import com.jason.system.model.mapper.SysConfigMapper;
import com.jason.system.model.mapper.SysUserMapper;
import com.jason.system.model.service.ISysUserService;
import com.jason.system.util.SecurityUtil;
import com.jason.system.util.ServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述:
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/9/18 22:52
 * @see
 */
@Service
class SysUserServiceImpl implements ISysUserService {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 根据条件查询用户
     *
     * @param sysUser
     * @return
     */
    @Override
    @DataScope(deptAlias = "d", userAlias = "u")
    @Log
    public List<SysUser> selectUserList(SysUser sysUser) {
        List<SysUser> list = userMapper.selectUserList(sysUser);
        for (SysUser user: list
             ) {
            List<SysRole> roleList = new ArrayList<>();
            for (int i = 0; i < 9; i++) {
                SysRole role = new SysRole();
                role.setRoleId((long) i);
                role.setRoleName("roleName " + i);
                roleList.add(role);
            }
            user.setRoles(roleList);
        }


        return list;
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    @Override
    public SysUser selectUserByUserName(String username) {
        return userMapper.selectUserByUserName(username);
    }

    /**
     * 校验用户是否有数据权限
     *
     * @param userId
     */
    @Override
    public void checkUserDataScope(Long userId) {
        //如果是admin账户则不进行数据过滤
        if (SysUser.isAdmin(SecurityUtil.getUserId())) {
            return;
        }

        SysUser user = new SysUser();
        user.setUserId(userId);
        List<SysUser> users = ApplicationContext.getAopProxy(this).selectUserList(new SysUser());


    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }


}
