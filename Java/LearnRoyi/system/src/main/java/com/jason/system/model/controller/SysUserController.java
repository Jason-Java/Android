package com.jason.system.model.controller;

import com.github.pagehelper.PageHelper;
import com.jason.system.aspectj.Log;
import com.jason.system.constant.LogAction;
import com.jason.system.model.domain.DataTableInfo;
import com.jason.system.model.domain.SysUser;
import com.jason.system.model.service.ISysUserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
}
