package com.jason.system.model.service;

import com.jason.system.model.body.RouterVo;
import com.jason.system.model.domain.SysMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface ISysMenuService {


    /**
     * 根据用户Id查询用户具有的菜单
     *
     * @param sysMenu 查询条件
     * @param userId 用户Id
     * @return 菜单集合
     */
    List<SysMenu> selectMenuListByUserId(  SysMenu sysMenu,  Long userId);

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
    List<SysMenu> selectRouteByUserId(Long userId);


    /**
     * 构建MenuTree
     *
     * @param menus 菜单集合
     * @return 具有父子关系的菜单树
     */
    public List<RouterVo> buildMenuTree(List<SysMenu> menus);

    /**
     * 根据Id获取菜单详情
     *
     * @param menuId 菜单Id
     * @return 返回菜单详情
     */
    SysMenu selectMenuById( Long menuId);

}
