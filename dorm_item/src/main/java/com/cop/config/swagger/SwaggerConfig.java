package com.cop.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment){


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //是否启用
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cop.controller"))
                //过滤
                //.paths()
                .build();
    }

    private ApiInfo apiInfo(){
        Contact contact = new Contact("jerryKB&&LY", "https://github.com/JerryKB", "2585388719@qq.com");
        return new ApiInfo(
                "SwaggerStudy",
                "study swagger first",
                "v1.0",
                "https://github.com/JerryKB",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}
