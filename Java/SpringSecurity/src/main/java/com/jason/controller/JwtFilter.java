package com.jason.controller;

import com.jason.domain.Menu;
import com.jason.domain.User;
import com.jason.mapper.UserMapper;
import com.jason.service.MenuService;
import com.jason.service.impl.MenuServiceImpl;
import com.jason.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// todo 定义拦截器
@Controller
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuService menuService;


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

        System.out.println(request.getRequestURI()+"  被拦截");

        String token = request.getHeader("Authorization");

        //没有token
        if (token == null || token.equals("")) {
            filterChain.doFilter(request, response);
            return;
        }

        //判断token是否过期
        if (jwtUtil.isTokenExpired(token)) {
            throw new RuntimeException("token已过期");
        }

        //解析token
        String userName = jwtUtil.getUsernameFromToken(token);


        //用户名解析失败
        if (userName == null) {
            throw new RuntimeException("非法token");
        }

        User user = userMapper.selectOne(userName);
        if (user == null) {
            throw new RuntimeException("用户未登录");
        }

        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        // todo 获取权限信息封装到下面
        List<Menu> menuList = menuService.getMenuByUserName(userName);

        if (Objects.isNull(menuList) || menuList.size() <= 0) {
            throw new RuntimeException("无权限");
        }

        for (Menu menu:menuList) {
            authorityList.add(new SimpleGrantedAuthority(menu.getPerms()));
        }

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user, null, authorityList);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);



        filterChain.doFilter(request, response);



    }
}
