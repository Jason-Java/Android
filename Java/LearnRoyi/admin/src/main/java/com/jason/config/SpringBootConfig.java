package com.jason.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.oas.annotations.EnableOpenApi;


@Configuration
@MapperScan("com.jason.**.mapper")
@ComponentScan(value={"com.jason"},excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Mapper.class}))
@EnableOpenApi
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableAspectJAutoProxy(exposeProxy = true,proxyTargetClass = true)
public class SpringBootConfig extends WebMvcConfigurationSupport {

    /**
     * 静态资源处理器
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        /** swagger配置 */
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }






}
