package com.feedback.feedbackplatform.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI feedbackPlatformOpenAPI() {

        // 🔐 JWT Security Scheme
        SecurityScheme jwtScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        return new OpenAPI()
                .info(new Info()
                        .title("Feedback Exchange Platform API")
                        .description(
                                "Backend APIs for Feedback Exchange Platform\n\n" +
                                        "Roles:\n" +
                                        "- DEVELOPER: Create projects, view feedback\n" +
                                        "- REVIEWER: Review projects, earn money\n" +
                                        "- ADMIN: Verify feedback, control payments"
                        )
                        .version("1.0.0")
                )
                // 🔥 Add JWT to Swagger
                .addSecurityItem(new SecurityRequirement().addList("JWT"))
                .schemaRequirement("JWT", jwtScheme);
    }
}
