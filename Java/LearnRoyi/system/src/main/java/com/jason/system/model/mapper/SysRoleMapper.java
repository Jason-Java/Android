package com.jason.system.model.mapper;

import com.jason.system.model.domain.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper {

    /**
     * 根据用户Id查询角色信息
     *
     * @param userId 用户id
     * @return 角色信息列表
     */
    List<SysRole> selectRoleByUserId(@Param("userId") Long userId);
}
