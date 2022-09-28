package com.jason.system.model.controller;

import com.jason.system.model.domain.AjaxResult;
import com.jason.system.model.domain.SysMenu;
import com.jason.system.model.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PreAuthorize("@permi.hasPermission('system:menu:query7')")
    @GetMapping("/list")
    public void list(SysMenu menu) {
        System.out.println("函数执行完毕");
    }

}
