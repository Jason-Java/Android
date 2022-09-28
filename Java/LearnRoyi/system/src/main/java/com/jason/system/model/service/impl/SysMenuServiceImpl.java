package com.jason.system.model.service.impl;

import com.jason.system.model.body.RouterVo;
import com.jason.system.model.domain.SysMenu;
import com.jason.system.model.domain.SysUser;
import com.jason.system.model.mapper.SysMenuMapper;
import com.jason.system.model.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;


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
     * 根据用户Id查询Menu 如果userId==null || userId=0 则返回全部
     *
     * @param userId 用户id
     * @return Menu集合
     */
    @Override
    public List<SysMenu> selectMenuByUserId(Long userId) {
        if (SysUser.isAdmin(userId)) {
            return menuMapper.selectMenuByUserId(null);
        }
        return menuMapper.selectMenuByUserId(userId);

    }

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
                findChild(routerVo, menus);
            } else {
                break;
            }
        }
        return routerVoList;
    }


    private void findChild(RouterVo parent, List<SysMenu> menus) {
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
                findChild(routerVo, menus);
            }

        }
    }


}
