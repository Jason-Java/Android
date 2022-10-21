package com.jason.system.model.service.impl;

import com.jason.system.model.domain.SysRole;
import com.jason.system.model.domain.SysUser;
import com.jason.system.model.mapper.SysConfigMapper;
import com.jason.system.model.mapper.SysUserMapper;
import com.jason.system.model.service.ISysUserService;
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
    public List<SysUser> selectUserList(SysUser sysUser) {
        List<SysUser> list = userMapper.selectUserList(sysUser);
        for (SysUser user : list) {

            List<SysRole> list12 = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                SysRole r = new SysRole();
                r.setRoleId((long) i);
                r.setRoleName("角色name "+i);
                list12.add(r);
            }
            user.setRoles(list12);
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


}
