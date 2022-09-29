package com.jason.system.model.controller;

import com.jason.system.aspectj.Log;
import com.jason.system.model.domain.AjaxResult;
import com.jason.system.model.domain.SysMenu;
import com.jason.system.model.service.ISysMenuService;
import com.jason.system.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>描述:菜单管理
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/9/28 20:32
 * @see
 */
@RestController
@RequestMapping("/system/menu")
public class SysMenuController {

    @Autowired
    private ISysMenuService menuService;

    /**
     * 获取菜单树列表
     * @param menu
     * @return
     */
    @PreAuthorize("@permi.hasPermission('system:menu:list7')")
    @GetMapping("/list")
    public AjaxResult list(SysMenu menu) {
        Long userId = SecurityUtil.getUserId();
       List<SysMenu> menuList= menuService.selectMenuListByUserId(menu, userId);
        return AjaxResult.success(menuService.buildMenuTree(menuList));
    }

    @PreAuthorize("@permi.hasPermission('system:menu:query')")
    @GetMapping("/{menuId}")
    @Log
    public AjaxResult getInfo(@PathVariable Long menuId) {
        return AjaxResult.success(menuService.selectMenuById(menuId));
    }

    /*@PreAuthorize("@permi.hasPermi('system:menu:add')")
    @PostMapping()
    public AjaxResult add()*/

}
