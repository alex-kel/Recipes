package ru.dz.recipes.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * Created by Alex on 30.04.16.
 */
@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    public Docket api() {
        new Docket(DocumentationType.SWAGGER_2)
                .ignoredParameterTypes(MetaClass.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ru.dz.recipes.controller"))
                .paths(PathSelectors.any())
                .build()
    }
}
