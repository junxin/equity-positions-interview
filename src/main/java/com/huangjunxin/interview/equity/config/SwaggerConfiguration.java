package com.huangjunxin.interview.equity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Filename: SwaggerConfiguration
 * Description:
 * Copyright: Copyright (c)2015
 * Company: ailng
 *
 * @author: 黄俊鑫
 * @version: 1.0 Create at: 2020年07月27日 16:01
 * <p/>
 * Modification History: Date Author Version Description
 * ------------------------------------------------------------------
 * 2020年07月27 黄俊鑫 1.0 1.0 Version
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api(){
        //添加head参数start
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();

        pars.add(tokenPar.build());
        //添加head参数end

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/.*"))
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo())
                .ignoredParameterTypes(ApiIgnore.class);
    }


    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder().license("Apache License Version 2.0").title("Equity positions project").description("接口文档").version("v1").build();
        return apiInfo; //termsOfServiceUrl
    }
}
