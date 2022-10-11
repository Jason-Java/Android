package com.jason.system.model.service;

import com.jason.system.model.body.RouterVo;
import com.jason.system.model.domain.SysMenu;

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

    /**
     * 检查菜单是否具有唯一性
     * @param sysMenu
     * @return
     */
    int checkMenuUnique(SysMenu sysMenu);

    /**
     * 新增菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int insertMenu(SysMenu menu);

    /**
     * 修改菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(SysMenu menu);

    /**
     * 是否有子菜单
     * @param menuId
     * @return
     */
    boolean hasChildByMenuId(Long menuId);

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    boolean  deleteMenu(Long menuId);

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
    boolean reallyDeleteMenu(Long menuId);

    /**
     * 菜单有分配
     * @param menuId
     * @return
     */
    boolean hasDistributionByMenuId(Long menuId);
}
