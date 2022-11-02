package com.jason.system.model.controller;

import com.jason.system.annotation.Log;
import com.jason.system.constant.LogAction;
import com.jason.system.model.domain.AjaxResult;
import com.jason.system.model.domain.DataTableInfo;
import com.jason.system.model.domain.SysRole;
import com.jason.system.model.domain.SysUser;
import com.jason.system.model.service.ISysPostService;
import com.jason.system.model.service.ISysRoleService;
import com.jason.system.model.service.ISysUserService;
import com.jason.system.util.ExcelUtil;
import com.jason.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ISysRoleService roleService;


    @Autowired
    private ISysPostService postService;



    @PreAuthorize("@permi.hasPermission('system:user:list')")
    @Log(title = "用户模块", action = LogAction.QUERY)
    @GetMapping("/list")
    public DataTableInfo list(SysUser user) {
        startPage();
        List<SysUser> userList = userService.selectUserList(user);
        return getDateTable(userList);
    }


    @PreAuthorize("@permi.hasPermission('system:user:export')")
    @Log(title = "用户模块", action = LogAction.EXPORT)
    @GetMapping("/export")
    public void export(HttpServletResponse response, SysUser user) {
        List<SysUser> userList = userService.selectUserList(user);
        ExcelUtil<SysUser> excelUtil = new ExcelUtil<>(SysUser.class);
        excelUtil.exportExcel(response, userList, "用户列表", "用户数据", null);
    }


    @GetMapping(value={"/", "/{userId}"})
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId) {
        userService.checkUserDataScope(userId);
        AjaxResult ajax = AjaxResult.success();
        List<SysRole> roles = roleService.selectRoleAll();
        if (!SysUser.isAdmin(getUserId())) {
            roles = roles.stream().filter(x -> !x.isAdmin()).collect(Collectors.toList());
        }
        ajax.put("roles",roles);
        ajax.put("posts",postService.selectPostAll());
        if (StringUtils.isNotNull(userId)) {
            SysUser user = userService.selectUserById(userId);
            ajax.put(AjaxResult.DATA_TAG, user);
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", user.getRoles().stream().map(SysRole::getRoleId).collect(Collectors.toList()));
        }
        return ajax;
    }


}
