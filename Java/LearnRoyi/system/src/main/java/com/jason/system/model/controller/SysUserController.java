package com.jason.system.model.controller;

import com.jason.system.annotation.Log;
import com.jason.system.constant.LogAction;
import com.jason.system.model.domain.AjaxResult;
import com.jason.system.model.domain.DataTableInfo;
import com.jason.system.model.domain.SysUser;
import com.jason.system.model.service.ISysUserService;
import com.jason.system.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>描述:用户控制类
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/11 22:37
 * @see
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {

    @Autowired
    private ISysUserService userService;


    @PreAuthorize("@permi.hasPermission('system:user:list')")
    @Log(title = "用户模块", action = LogAction.QUERY)
    @GetMapping("/list")
    public DataTableInfo list(SysUser user) {
        startPage();
        List<SysUser> userList = userService.selectUserList(user);
        return getDateTable(userList);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response, SysUser user) {
        List<SysUser> userList = userService.selectUserList(user);
        ExcelUtil<SysUser> excelUtil = new ExcelUtil<>(SysUser.class);
        excelUtil.exportExcel(response, userList, "用户列表");
    }

}
