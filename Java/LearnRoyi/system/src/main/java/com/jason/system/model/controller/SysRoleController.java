package com.jason.system.model.controller;

import com.jason.system.annotation.Log;
import com.jason.system.model.domain.AjaxResult;
import com.jason.system.model.domain.DataTableInfo;
import com.jason.system.model.domain.SysRole;
import com.jason.system.model.service.ISysRoleService;
import com.jason.system.model.service.impl.SysRoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>描述:角色控制类
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/12 08:40
 * @see
 */
@RestController
@RequestMapping("/system/role")
public class SysRoleController extends BaseController {


   @Autowired
    private ISysRoleService roleService;

    /**
     * 查询角色列表
     * @param role
     * @return
     */
    @PreAuthorize("@permi.hasPermission('system:role:list')")
    @GetMapping
    public DataTableInfo list(SysRole role) {
        startPage();
        List<SysRole> roleList = roleService.selectRoleList(role);
       return getDateTable(roleList);
    }


    @GetMapping(value = "/{roleId}")
    public AjaxResult getInfo(@PathVariable(value = "roleId") Long roleId) {

        roleService.checkRoleDataScope(roleId);
        return AjaxResult.success(roleService.selectRoleByRoleId(roleId));
    }

}