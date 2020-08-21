/*
Project .....................: netPos
Creation Date ...............: 19/08/2020 12:30:11
Developer....................: ederluciano
Copyright....................: 2020
Codification.................: UTF-8
...........................................................
 Éder L. Costa - © Copyright 2020 - All Rights Reserved
...........................................................
*/
package br.com.edersystems.challenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.edersystems.challenge.controllers"))
                .build()
                .enableUrlTemplating(true)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("Éder Costa", "https://www.linkedin.com/in/%C3%A9der-luciano-da-costa-14172730/", "miibx5@gmail.com"))
                .title("NetPos Challenge")
                .description("Create API for product and account management")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}
