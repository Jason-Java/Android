package com.jason.system.security;

import com.jason.system.model.domain.SysRole;
import com.jason.system.model.domain.SysUser;
import com.jason.system.util.SecurityUtil;
import com.jason.system.util.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.Arrays;

/**
 * <p>描述:自定义权限认证
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/9/28 20:42
 * @see
 */
@Service("permi")
public class PermissionService {
    /** 所有权限标识 */
    private static final String ALL_PERMISSION = "*:*:*";

    /** 管理员角色权限标识 */
    private static final String SUPER_ADMIN = "admin";

    private static final String ROLE_DELIMETER = ",";

    private static final String PERMISSION_DELIMETER = ",";

    public boolean hasPermission(String permi) {

        if (StringUtils.isEmpty(permi)) {
            return false;
        }
        if (StringUtils.isEmpty(SecurityUtil.getPermission())) {
            return false;
        }
        for (SysRole role : SecurityUtil.getRoles()) {
            if (role.getRoleKey().equals(SUPER_ADMIN)) {
                return true;
            }
        }

        return SecurityUtil.getPermission().stream().filter(x->x.equals(permi)).count()>0;
    }

    public boolean hasAnyPermission(String... permiss) {
        if (StringUtils.isEmpty(permiss)) {
            return false;
        }
        if (StringUtils.isEmpty(SecurityUtil.getPermission())) {
            return false;
        }
        for (SysRole role : SecurityUtil.getRoles()) {
            if (role.getRoleKey().equals(SUPER_ADMIN)) {
                return true;
            }
        }

        return SecurityUtil.getPermission().stream().filter(x->Arrays.asList(permiss).contains(x)).count()>0;
    }

}
