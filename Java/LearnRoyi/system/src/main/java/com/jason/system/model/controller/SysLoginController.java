package com.jason.system.model.controller;


import com.jason.system.annotation.Log;
import com.jason.system.constant.Constants;
import com.jason.system.constant.LogAction;
import com.jason.system.model.body.LoginBody;
import com.jason.system.model.domain.AjaxResult;
import com.jason.system.model.domain.SysMenu;
import com.jason.system.model.domain.SysUser;
import com.jason.system.model.service.ISysRoleService;
import com.jason.system.model.service.SysLoginService;
import com.jason.system.model.service.impl.SysMenuServiceImpl;
import com.jason.system.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@RestController
public class SysLoginController {
    @Autowired
    private SysLoginService loginService;

    @Resource(name = "SysRoleServiceImpl")
    private ISysRoleService roleService;

    @Autowired
    private SysMenuServiceImpl menuService;


    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @ApiOperation("登陆")
    @Log(title = "登录")
    @PostMapping("/login")
    public AjaxResult login(@Validated @RequestBody LoginBody loginBody) {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }


    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @ApiOperation("根据token获取用户信息")
    @Log(title = "获取用户信息",action =LogAction.QUERY )
    @GetMapping("/getInfo")
    public AjaxResult getInfo() {
        SysUser sysUser = SecurityUtil.getLoginUser().getUser();
        //角色集合
        Set<String> roleKeySet = roleService.selectRoleKeyByUserId(SecurityUtil.getLoginUser().getUserId());
        // 权限集合
        List<String> perms = menuService.selectMenuPermsByUserId(SecurityUtil.getLoginUser().getUserId());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", sysUser);
        ajax.put("roles", roleKeySet);
        ajax.put("permissions", perms);
        return ajax;
    }

    @Log(title = "获取角色信息",action =LogAction.QUERY)
    @GetMapping("/getRouters")
    public AjaxResult getRouters() {
        Long userId = SecurityUtil.getLoginUser().getUserId();
        List<SysMenu> menuList = menuService.selectRouteByUserId(userId);
        return AjaxResult.success(menuService.buildMenuTree(menuList));
    }
}
