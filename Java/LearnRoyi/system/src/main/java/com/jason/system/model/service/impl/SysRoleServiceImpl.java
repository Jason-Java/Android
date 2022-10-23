package com.jason.system.model.service.impl;

import com.jason.ApplicationContext;
import com.jason.system.annotation.DataScope;
import com.jason.system.model.domain.SysRole;
import com.jason.system.model.domain.SysUser;
import com.jason.system.model.mapper.SysRoleMapper;
import com.jason.system.model.service.ISysRoleService;
import com.jason.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("SysRoleServiceImpl")
public class SysRoleServiceImpl implements ISysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;



    /**
     * 获取角色列表
     *
     * @param role
     * @return
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<SysRole> selectRoleList(SysRole role) {
        return roleMapper.selectRoleList(role);
    }

    /**
     * 根据用户Id查询角色信息
     *
     * @param userId 用户id
     * @return 角色信息列表
     */
    @Override
    public List<SysRole> selectRoleByUserId(Long userId) {
        return roleMapper.selectRoleByUserId(userId);
    }


    /**
     * 根据用户Id获取RoleKey
     *
     * @param userId 用户Id
     * @return RoleKey集合
     */
    public Set<String> selectRoleKeyByUserId(Long userId) {

        Set<String> roleKey = new HashSet<>();
        if (SysUser.isAdmin(userId)) {
            roleKey.add("admin");
            return roleKey;
        }
        List<SysRole> roles = selectRoleByUserId(userId);
        for (SysRole role : roles) {
            if (StringUtils.isNotNull(role.getRoleKey())) {
                roleKey.addAll(Arrays.asList(role.getRoleKey().trim().split(",")));
            }
        }
        return roleKey;
    }

    @Override
    public List<SysRole> selectRoleAll() {
        return ApplicationContext.getAopProxy(this).selectRoleList(new SysRole());
    }

}
