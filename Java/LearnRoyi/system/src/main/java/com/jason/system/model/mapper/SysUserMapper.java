package com.jason.system.model.mapper;

import com.jason.system.model.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {

   List<SysUser> selectUserList(SysUser sysUser);


   SysUser selectUserByUserName(String username);

}
