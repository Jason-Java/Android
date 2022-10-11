package com.jason.system.model.mapper;

/**
 * <p>描述:
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/11 20:43
 * @see
 */
public interface SysRoleMenuMapper {

    /**
     * 菜单有分配
     * @param menuId
     * @return
     */
    int menuHasDistribution(Long menuId);
}
