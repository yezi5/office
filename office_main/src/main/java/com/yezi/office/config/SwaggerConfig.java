package com.yezi.office.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author 叶子
 * @Description Swagger配置文件
 * @PackageName com.yezi.office.config
 * @DevelopmentTools IntelliJ IDEA
 * @Data 2020/12/29 星期二 16:36
 */
@Component
@Configurable
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("协同办公系统接口文档")
                .contact(new Contact("叶子","http://www.yezi.icu/","2693830285@qq.com"))
                .description("协同办公系统前后端分离使用接口文档")
                .version("1.0")
                .build();
    }
}
