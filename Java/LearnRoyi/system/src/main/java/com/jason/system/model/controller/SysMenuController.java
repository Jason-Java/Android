package com.jason.system.model.controller;

import com.jason.system.aspectj.Log;
import com.jason.system.constant.LogAction;
import com.jason.system.constant.UserConstant;
import com.jason.system.model.domain.AjaxResult;
import com.jason.system.model.domain.SysMenu;
import com.jason.system.model.service.ISysMenuService;
import com.jason.system.util.SecurityUtil;
import com.jason.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
public class SysMenuController extends BaseController {

    @Autowired
    private ISysMenuService menuService;

    /**
     * 获取菜单树列表
     *
     * @param menu
     * @return
     */
    @PreAuthorize("@permi.hasPermission('system:menu:list')")
    @Log(title = "菜单", action = LogAction.QUERY)
    @GetMapping("/list")
    public AjaxResult list(SysMenu menu) {
        Long userId = SecurityUtil.getUserId();
        List<SysMenu> menuList = menuService.selectMenuListByUserId(menu, userId);
        return AjaxResult.success(menuService.buildMenuTree(menuList));
    }

    /**
     * 根据菜单ID获取菜单详情
     *
     * @param menuId
     * @return
     */
    @PreAuthorize("@permi.hasPermission('system:menu:query')")
    @GetMapping("/{menuId}")
    @Log(title = "菜单", action = LogAction.QUERY)
    public AjaxResult getInfo(@PathVariable Long menuId) {
        return AjaxResult.success(menuService.selectMenuById(menuId));
    }

    /**
     * 添加菜单
     *
     * @return
     */
    @PreAuthorize("@permi.hasPermission('system:menu:add')")
    @Log(title = "菜单", action = LogAction.INSERT)
    @PostMapping()
    public AjaxResult add(@Validated @RequestBody SysMenu sysMenu) {

        if (UserConstant.NOT_UNIQUE == menuService.checkMenuUnique(sysMenu)) {
            AjaxResult.error("菜单名称重复 " + sysMenu.getMenuName());
        } else if (UserConstant.YES_FRAME.equals(sysMenu.getIsFrame()) && StringUtils.ishttp(sysMenu.getPath())) {
            return AjaxResult.error("新增菜单'" + sysMenu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }

        return  menuService.insertMenu(sysMenu)>0? AjaxResult.success("添加成功"):AjaxResult.error("添加失败，请稍候重试");
    }

    /**
     * 修改菜单
     * @param menu
     * @return
     */
    @PreAuthorize("@permi.hasPermission('system:menu:edit')")
    @Log(title = "菜单", action = LogAction.UPDATE)
    @PutMapping
    public AjaxResult update(@Validated @RequestBody SysMenu menu) {
        if (UserConstant.NOT_UNIQUE == menuService.checkMenuUnique(menu)) {
            AjaxResult.error("菜单名称重复 " + menu.getMenuName());
        } else if (UserConstant.YES_FRAME.equals(menu.getIsFrame()) && StringUtils.ishttp(menu.getPath())) {
            return AjaxResult.error("新增菜单'" + menu.getMenuName() + "'失败，地址必须以http(s)://开头");
        }else if (menu.getMenuId().equals(menu.getParentId()))
        {
            return AjaxResult.error("修改菜单'" + menu.getMenuName() + "'失败，上级菜单不能选择自己");
        }
        menu.setUpdateBy(getUserName());
        return menuService.updateMenu(menu)>0?AjaxResult.success("修改成功"):AjaxResult.error("修改失败，请稍候重试");
    }


    @Log(title = "菜单", action = LogAction.DELETE)
    @PreAuthorize("@permi.hasPermission('system:menu:remove')")
    @DeleteMapping("/{menuId}")
    public AjaxResult delete(@PathVariable Long menuId){
        if (menuService.hasChildByMenuId(menuId)) {
            AjaxResult.error("有子菜单不允许删除" );
        } else if (menuService.hasDistributionByMenuId(menuId)) {
            AjaxResult.error("菜单已分配不允许删除" );
        }
        return menuService.deleteMenu(menuId)?AjaxResult.success("删除成功"):AjaxResult.error("删除失败");
    }

}
