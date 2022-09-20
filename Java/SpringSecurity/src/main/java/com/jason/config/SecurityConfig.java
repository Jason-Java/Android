package com.jason.config;

import com.jason.controller.JwtFilter;
import com.jason.security.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// TODO: 2022/9/6 开启权限控制
@EnableGlobalMethodSecurity(prePostEnabled = true)

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


   /* @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭scrf
                .csrf().disable()
                //不通过Session过去SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                //允许登录接口不进行验证   anonymous 表示匿名登录  已经登录不能在访问     permitAll 表示 无论是否登录都可访问
                .antMatchers("/logins", "/logins/**").permitAll()
                //除了登录接口都需要验证
                .anyRequest().authenticated();

        // todo 注册拦截器
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        // todo 添加认证异常处理器
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        // todo 添加授权异常处理器
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);


        //设置允许跨域
        http.cors();

    }

    // todo 开启授权管理
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
