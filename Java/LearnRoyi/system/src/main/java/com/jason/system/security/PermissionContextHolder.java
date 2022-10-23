package com.jason.system.security;

import com.jason.system.util.ServletUtil;
import org.apache.commons.compress.archivers.dump.DumpArchiveEntry;
import org.springframework.web.context.request.RequestAttributes;

/**
 * <p>描述:权限信息
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/23 16:07
 * @see
 */
public class PermissionContextHolder {

    private static final String PERMISSION_CONTEXT_ATTRIBUTES = "PERMISSION_CONTEXT";

    /**
     * 设置 request Attribute 权限值
     *
     * @param permission 权限值
     */
    public static void setPermission(String permission) {
        ServletUtil.getRequestAttributes().setAttribute(PERMISSION_CONTEXT_ATTRIBUTES, permission, RequestAttributes.SCOPE_REQUEST);
    }

    /**
     * 获取 request Attribute 权限值
     */
    public static String getPermission() {
        return ServletUtil.getRequestAttributes().getAttribute(PERMISSION_CONTEXT_ATTRIBUTES, RequestAttributes.SCOPE_REQUEST).toString();
    }
}
