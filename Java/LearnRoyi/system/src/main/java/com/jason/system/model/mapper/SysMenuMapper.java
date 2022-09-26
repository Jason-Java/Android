package com.jason.system.model.mapper;

import com.jason.system.model.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysMenuMapper {

    /**
     * 根据角色id获取权限信息
     *
     * @param userId 用户Id
     * @return 权限信息集合
     */
    List<String> selectMenuPermsByUserId(@Param("userId") Long userId);

    /**
     * 根据用户Id查询Menu 如果userId==null || userId=0 则返回全部
     * @param userId 用户id
     * @return Menu集合
     */
    List<SysMenu> selectMenuByUserId(@Param("userId") Long userId);
}
