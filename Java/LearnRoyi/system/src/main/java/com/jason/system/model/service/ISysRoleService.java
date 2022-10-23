package com.jason.system.model.service;

import com.jason.system.model.domain.SysRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;


public interface ISysRoleService {



    /**
     * 获取角色列表
     * @param role
     * @return
     */
    List<SysRole> selectRoleList(SysRole role);

    /**
     * 根据用户Id查询角色信息
     *
     * @param userId 用户id
     * @return 角色信息列表
     */
    List<SysRole> selectRoleByUserId(  Long userId);

    /**
     * 根据用户Id查询RoleKey
     *
     * @param userId 用户id
     * @return RoleKey列表
     */
    Set<String> selectRoleKeyByUserId(Long userId);

    List<SysRole> selectRoleAll();
}
