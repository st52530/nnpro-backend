package cz.upce.vetalmael.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    public static String SWAGGER_AUTH_KEY = "bearerAuth";

    @Bean
    OpenAPI customOpenAPI() {
        SecurityScheme scheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
        return new OpenAPI()
                .components(new Components().addSecuritySchemes(SWAGGER_AUTH_KEY, scheme));
    }
}
