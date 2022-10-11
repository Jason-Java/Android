package com.jason.system.model.service.impl;

import com.jason.system.constant.UserConstant;
import com.jason.system.model.body.RouterVo;
import com.jason.system.model.domain.SysMenu;
import com.jason.system.model.domain.SysUser;
import com.jason.system.model.mapper.SysMenuMapper;
import com.jason.system.model.mapper.SysRoleMenuMapper;
import com.jason.system.model.service.ISysMenuService;
import com.jason.system.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Autowired
    private SysRoleMenuMapper roleMenuMapper;

    /**
     * 根据用户Id查询用户具有的菜单
     *
     * @param sysMenu 查询条件
     * @param userId  用户Id
     * @return 菜单集合
     */
    @Override
    public List<SysMenu> selectMenuListByUserId(SysMenu sysMenu, Long userId) {

        if (SysUser.isAdmin(userId)) {
            userId = null;
        }
        return menuMapper.selectMenuListByUserId(sysMenu, userId);
    }


    /**
     * 根据角色id获取权限信息
     *
     * @param userId 用户Id
     * @return 权限信息集合
     */
    @Override
    public List<String> selectMenuPermsByUserId(Long userId) {
        List<String> perms = new ArrayList<>();
        if (SysUser.isAdmin(userId)) {
            perms.add("*:*:*");
            return perms;
        }
        perms.addAll(menuMapper.selectMenuPermsByUserId(userId));
        return perms;
    }

    /**
     * 根据用户Id查询Route 如果userId==null || userId=0 则返回全部
     *
     * @param userId 用户id
     * @return Menu集合
     */
    @Override
    public List<SysMenu> selectRouteByUserId(Long userId) {
        if (SysUser.isAdmin(userId)) {
            return menuMapper.selectRouteByUserId(null);
        }
        return menuMapper.selectRouteByUserId(userId);

    }

    /**
     * 构建MenuTree
     *
     * @param menus 菜单集合
     * @return 具有父子关系的菜单树
     */
    public List<RouterVo> buildMenuTree(List<SysMenu> menus) {
        List<RouterVo> routerVoList = new ArrayList<>();
        RouterVo routerVo = null;
        for (int i = 0; i < menus.size(); i++) {
            SysMenu menu = menus.get(i);
            routerVo = new RouterVo();
            routerVo.setId(menu.getMenuId());
            routerVo.setName(menu.getMenuName());
            routerVo.setPath(menu.getPath());
            routerVo.setQuery(menu.getQuery());
            routerVo.setComponent(menu.getComponent());
            if (menu.getParentId() == 0) {
                routerVoList.add(routerVo);
                menus.remove(i);
                i--;
                buildMenuTree(routerVo, menus);
            } else {
                break;
            }
        }

        if (menus.size() <= 0) {
            return routerVoList;
        }

        for (int i = 0; i < menus.size(); i++) {
            SysMenu menu = menus.get(i);
            routerVo = new RouterVo();
            routerVo.setId(menu.getMenuId());
            routerVo.setName(menu.getMenuName());
            routerVo.setPath(menu.getPath());
            routerVo.setQuery(menu.getQuery());
            routerVo.setComponent(menu.getComponent());
            routerVoList.add(routerVo);
            menus.remove(i);
            i--;
            buildMenuTree(routerVo, menus);
        }
        return routerVoList;
    }


    /**
     * 构建menuTrue
     *
     * @param parent
     * @param menus
     */
    private void buildMenuTree(RouterVo parent, List<SysMenu> menus) {
        RouterVo routerVo = null;
        for (int i = 0; i < menus.size(); i++) {
            if (parent.getId() == menus.get(i).getParentId()) {
                if (parent.getChildren() == null)
                    parent.setChildren(new ArrayList<RouterVo>());
                routerVo = new RouterVo();
                routerVo.setId(menus.get(i).getMenuId());
                routerVo.setName(menus.get(i).getMenuName());
                routerVo.setPath(menus.get(i).getPath());
                routerVo.setQuery(menus.get(i).getQuery());
                routerVo.setComponent(menus.get(i).getComponent());
                menus.remove(i);
                i--;
                parent.getChildren().add(routerVo);
                buildMenuTree(routerVo, menus);
            }
        }
    }

    /**
     * 根据Id获取菜单详情
     *
     * @param menuId 菜单Id
     * @return 返回菜单详情
     */
    @Override
    public SysMenu selectMenuById(Long menuId) {
        return menuMapper.selectMenuById(menuId);
    }


    /**
     * 检查菜单是否具有唯一性
     *
     * @param sysMenu
     * @return
     */
    @Override
    public int checkMenuUnique(SysMenu sysMenu) {
        return menuMapper.checkMenuUnique(sysMenu) > 0 ? UserConstant.NOT_UNIQUE : UserConstant.UNIQUE;
    }

    /**
     * 新增菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int insertMenu(SysMenu menu) {
        return menuMapper.insertMenu(menu);
    }

    /**
     * 修改菜单信息
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(SysMenu menu) {
        return menuMapper.updateMenu(menu);
    }

    /**
     * 是否有子菜单
     *
     * @param menuId
     * @return
     */
    @Override
    public boolean hasChildByMenuId(Long menuId) {
        return menuMapper.hasChildByMenuId(menuId) > 0;
    }

    /**
     * 删除菜单---修改菜单状态
     *
     * @param menuId
     * @return
     */
    @Override
    public boolean deleteMenu(Long menuId) {
        SysMenu menu = new SysMenu();
        menu.setMenuId(menuId);
        menu.setUpdateBy(SecurityUtil.getUserName());
        menu.setStatus("1");
        return menuMapper.updateMenu(menu) > 0;
    }

    /**
     * 删除菜单--真正删除
     *
     * @param menuId
     * @return
     */
    @Override
    public boolean reallyDeleteMenu(Long menuId) {
        return menuMapper.deleteMenu(menuId) > 0;
    }

    /**
     * 菜单有分配
     *
     * @param menuId
     * @return
     */
    @Override
    public boolean hasDistributionByMenuId(Long menuId) {
        return roleMenuMapper.menuHasDistribution(menuId) > 0;
    }

}
