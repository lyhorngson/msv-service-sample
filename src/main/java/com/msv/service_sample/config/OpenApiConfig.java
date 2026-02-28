package com.msv.service_sample.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Value("${app.api.version:1.0.0}")
    private String version;

    @Value("${app.api.description:API Documentation}")
    private String description;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(appName)
                        .version(version)
                        .description(description)
                        .contact(new Contact()
                                .name("Engineering Team")));
    }
}