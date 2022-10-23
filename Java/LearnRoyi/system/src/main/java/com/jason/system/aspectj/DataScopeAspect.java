package com.jason.system.aspectj;

import com.jason.system.annotation.DataScope;
import com.jason.system.model.body.LoginUser;
import com.jason.system.model.domain.BaseDomain;
import com.jason.system.model.domain.SysRole;
import com.jason.system.model.domain.SysUser;
import com.jason.system.security.PermissionContextHolder;
import com.jason.system.util.SecurityUtil;
import com.jason.system.util.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>描述:检查数据范围Aop
 *
 *
 * <p>邮箱: fjz19971129@163.comcontrollerDataScope
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/23 14:09
 * @see
 */
@Component
@Aspect
public class DataScopeAspect {


    /**
     * 全部数据权限
     */
    public static final String DATA_SCOPE_ALL = "1";

    /**
     * 自定数据权限
     */
    public static final String DATA_SCOPE_CUSTOM = "2";

    /**
     * 部门数据权限
     */
    public static final String DATA_SCOPE_DEPT = "3";

    /**
     * 部门及以下数据权限
     */
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";

    /**
     * 仅本人数据权限
     */
    public static final String DATA_SCOPE_SELF = "5";

    /**
     * 数据权限过滤关键字
     */
    public static final String DATA_SCOPE = "dataScope";

    @Pointcut("@annotation(com.jason.system.annotation.DataScope)")
    public void point() {
    }


    @Before("point()")
    private void doBefore(final JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        DataScope dataScope = method.getAnnotation(DataScope.class);
        clearDataScope(joinPoint);
        RequestContextHolder.currentRequestAttributes().getAttribute("PERMISSION_CONTEXT",
                RequestAttributes.SCOPE_REQUEST);

        disposeDataScope(joinPoint, dataScope);

        System.out.println("数据范围切入点开始执行");

    }

    private void disposeDataScope(final JoinPoint point, final DataScope dataScope) {
        //获取当前用户
        LoginUser loginUser = SecurityUtil.getLoginUser();
        // 如果是超级管理员则不处理数据
        if (loginUser == null || SysUser.isAdmin(loginUser.getUserId()))
            return;

        //   StringUtils.defaultString(dataScope.permission(),Permission)
        String permission =
                StringUtils.isEmpty(dataScope.permission()) ? dataScope.permission() : PermissionContextHolder.getPermission();
        dataScopeFilter(point, loginUser.getUser(), dataScope.deptAlias(), dataScope.userAlias(), permission);

    }

    private void dataScopeFilter(JoinPoint joinPoint, SysUser user, String deptAlias, String userAlias, String permission) {
        StringBuilder sqlString = new StringBuilder();
        List<String> conditions = new ArrayList<String>();

        for (SysRole role : user.getRoles()) {
            String dataScope = role.getDataScope();
            if (!DATA_SCOPE_CUSTOM.equals(dataScope) && conditions.contains(dataScope)){
                continue;
            }

            if (StringUtils.isNotEmpty(permission) && StringUtils.isNotEmpty(role.getPermissions())
                    && !role.getPermissions().contains(permission)) {
                continue;
            }
            if (DATA_SCOPE_ALL.equals(dataScope)) {
                sqlString = new StringBuilder();
                break;
            } else if (DATA_SCOPE_CUSTOM.equals(dataScope)) {
                sqlString.append(StringUtils.format(
                        " OR {}.dept_id IN ( SELECT dept_id FROM sys_role_dept WHERE role_id = {} ) ",
                        deptAlias,role.getRoleId()));
            } else if (DATA_SCOPE_DEPT.equals(dataScope)) {
                sqlString.append(StringUtils.format(" OR {}.dept_id = {} ", deptAlias, user.getDeptId()));
            }else if (DATA_SCOPE_DEPT_AND_CHILD.equals(dataScope))
            {
                sqlString.append(StringUtils.format(
                        " OR {}.dept_id IN ( SELECT dept_id FROM sys_dept WHERE dept_id = {} or find_in_set( {} , ancestors ) )",
                        deptAlias, user.getDeptId(), user.getDeptId()));
            } else if (DATA_SCOPE_SELF.equals(dataScope)) {
                if (StringUtils.isNotBlank(userAlias))
                {
                    sqlString.append(StringUtils.format(" OR {}.user_id = {} ", userAlias, user.getUserId()));
                } else
                {
                    // 数据权限为仅本人且没有userAlias别名不查询任何数据
                    sqlString.append(StringUtils.format(" OR {}.dept_id = 0 ", deptAlias));
                }
            }
            conditions.add(dataScope);
        }
        if (StringUtils.isNotBlank(sqlString.toString()))
        {
            Object params = joinPoint.getArgs()[0];
            if (StringUtils.isNotNull(params) && params instanceof BaseDomain)
            {
                BaseDomain baseEntity = (BaseDomain) params;
                baseEntity.getParams().put(DATA_SCOPE, " AND (" + sqlString.substring(4) + ")");
            }
        }

    }


    private void clearDataScope(final JoinPoint point) {
        Object param = point.getArgs()[0];
        if (StringUtils.isNotNull(param) && param instanceof BaseDomain) {
            BaseDomain domain = (BaseDomain) param;
            domain.getParams().put(DATA_SCOPE, "");
        }
    }

}
