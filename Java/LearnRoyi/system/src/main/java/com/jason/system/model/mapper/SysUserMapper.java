package com.jason.system.model.mapper;

import com.jason.system.model.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {

   /**
    * 获取用户列表
    * @param sysUser
    * @return
    */
   List<SysUser> selectUserList(SysUser sysUser);


   /**
    * 根据用户名查询用户
    * @param username
    * @return
    */
   SysUser selectUserByUserName(String username);

}
