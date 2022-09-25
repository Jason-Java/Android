package com.jason.config;

import com.jason.system.exception.SecurityGlobalException;
import com.jason.system.security.UserDetailServiceImpl;
import com.jason.system.security.filter.AuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //JWT 认证拦截器
    @Autowired
    private AuthenticationTokenFilter authenticationTokenFilter;
    //认证失败处理器
    @Autowired
    private SecurityGlobalException securityGlobalException;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.cors().disable();

        //添加认证失败处理类
        http.exceptionHandling().authenticationEntryPoint(securityGlobalException);

        //过滤请求资源
        http.
                authorizeRequests()
                //登陆接口可以匿名访问
                .antMatchers("/login", "/login/**").anonymous()
                //静态资源可以允许访问
                .antMatchers("/swagger-ui","/swagger-ui/**").permitAll()
                .antMatchers(HttpMethod.GET, "/", "/**/*.html", "/**/*.js", "profile/**").permitAll()
                .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
                .antMatchers("/swagger-ui/**","/swagger-ui.html", "/swagger-resources/**", "/webjars/**", "/*/api-docs", "/druid/**").permitAll()
                //除了上述资源其余资源都需要认证
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable();

        //不主动创建Session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        //添加JWT 过滤器
        http.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);



    }


    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(getBCryptPasswordEncoder());

    }

    @Bean
    public AuthenticationManager getAuthentication() throws Exception {
        return super.authenticationManagerBean();
    }
}
