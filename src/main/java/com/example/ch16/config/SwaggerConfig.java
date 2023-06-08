package com.example.ch16.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.security.config.Elements.JWT;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        Components components = new Components()
                .addSecuritySchemes(JWT, getJwtSecurityScheme());
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(JWT);
        Info info = new Info()
                .version("v1.0.0")
                .title("CH 16 API")
                .description("API Description");

        return new OpenAPI()
                .info(info)
                .components(components)
                .addSecurityItem(securityRequirement);
    }

    private SecurityScheme getJwtSecurityScheme() {
        return new SecurityScheme().type(Type.APIKEY).in(In.HEADER).name("X-AUTH-TOKEN");
    }
}