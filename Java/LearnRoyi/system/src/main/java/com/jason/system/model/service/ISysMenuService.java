package com.jason.system.model.service;

import com.jason.system.model.domain.SysMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

import java.util.List;
public interface ISysMenuService {
    /**
     * 根据角色id获取权限信息
     *
     * @param userId 用户Id
     * @return 权限信息集合
     */
    List<String> selectMenuPermsByUserId(Long userId);


    /**
     * 根据用户Id查询Menu 如果userId==null || userId=0 则返回全部
     * @param userId 用户id
     * @return Menu集合
     */
    List<SysMenu> selectMenuByUserId(  Long userId);

}
