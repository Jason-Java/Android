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
import java.util.List;

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

    public List<SysMenu> buildMenuTree(int parentId,List<SysMenu> menus) {
        List<RouterVo> routerVoList = new ArrayList<>();
        RouterVo routerVo = null;
        for (SysMenu menu : menus) {
            routerVo = new RouterVo();
            routerVo.setName(menu.getMenuName());
            routerVo.setPath(menu.getPath());
            routerVo.setQuery(menu.getQuery());
            routerVo.setComponent(menu.getComponent());
            if (menu.getParentId() == 0) {
                List<RouterVo> childRouter = new ArrayList<>();
                routerVo.setChildren(childRouter);
                routerVoList.add(routerVo);
                menus.remove(menu);
            }

        }
        return null;
    }







}
