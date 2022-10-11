package com.jason.system.model.mapper;

import com.jason.system.model.domain.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface SysMenuMapper {

    /**
     * 根据用户Id查询用户具有的菜单
     *
     * @param sysMenu 查询条件
     * @param userId 用户Id
     * @return 菜单集合
     */
    List<SysMenu> selectMenuListByUserId(@Param("sysMenu") SysMenu sysMenu, @Param("userId") Long userId);

    /**
     * 根据角色id获取权限信息
     *
     * @param userId 用户Id
     * @return 权限信息集合
     */
    List<String> selectMenuPermsByUserId(@Param("userId") Long userId);

    /**
     * 根据用户Id查询路由 如果userId==null || userId=0 则返回全部
     * @param userId 用户id
     * @return Menu集合
     */
    List<SysMenu> selectRouteByUserId(@Param("userId") Long userId);

    /**
     * 根据Id获取菜单详情
     *
     * @param menuId 菜单Id
     * @return 返回菜单详情
     */
    SysMenu selectMenuById(@Param("menuId") Long menuId);

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
    int hasChildByMenuId(Long menuId);

    /**
     * 删除菜单
     * @param menuId
     * @return
     */
   int  deleteMenu(Long menuId);

}
