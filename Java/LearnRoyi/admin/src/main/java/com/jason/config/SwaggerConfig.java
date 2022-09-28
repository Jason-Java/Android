package com.jason.config;


import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * <p>描述:Swagger Api config
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/9/25 14:41
 * @see
 */
@Configuration

public class SwaggerConfig {



    @Bean
    public Docket getDocket() {
        return new Docket(DocumentationType.OAS_30)
                .enable(true)
                .apiInfo(getInfo())
                .groupName("jason API")
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                ;
    }


    private ApiInfo getInfo() {
        return new ApiInfoBuilder()
                .title("Swagger3接口文档")
                .description("如有疑问,可联系百度")
                .contact(new Contact("李白","http://www.baidu.com","baidu@qq.com"))
                .version("1.0")
                .build();
    }

}
