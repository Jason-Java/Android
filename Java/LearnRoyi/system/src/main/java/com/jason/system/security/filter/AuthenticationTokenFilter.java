package com.jason.system.security.filter;

import com.jason.system.exception.SecurityException;
import com.jason.system.exception.ServiceException;
import com.jason.system.model.body.LoginUser;
import com.jason.system.model.domain.SysUser;
import com.jason.system.model.service.ISysMenuService;
import com.jason.system.model.service.ISysUserService;
import com.jason.system.util.StringUtils;
import com.jason.system.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private TokenUtil tokenUtil;
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysMenuService menuService;

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //如果为登陆则进行登陆



        /**
         * 没有token进行放行
         */
        if (!tokenUtil.isHaveToken(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        // token无效
        if (tokenUtil.isTokenExpired(request)) {
            throw new ServiceException("token无效", 403);
        }

        SysUser user = userService.selectUserByUserName(tokenUtil.getUserNameFromToken(request));
        if (StringUtils.isNull(user)) {
            throw new SecurityException(403, "用户不存在");
        }

        //  查询权限
        List<String> perms=  menuService.selectMenuPermsByUserId(user.getUserId());
        LoginUser loginUser = new LoginUser(user, perms);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        //刷新token
        tokenUtil.refreshToken(request, response);
        //如果登陆了放行
        filterChain.doFilter(request, response);
    }
}
