package com.company.devicemanagement.config;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public org.springdoc.core.models.GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("app-apis").pathsToMatch("/api/**").build();

    }

}
