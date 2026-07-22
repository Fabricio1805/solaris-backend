package com.solaris.backend.infrastructure.config.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Solaris API")
                        .version("1.0.0")
                        .description("API para gerenciamento do sistema Solaris")
                        .contact(new Contact()
                                .name("Fabricio Souza")
                                .email("fabricio.ss09@outlook.com")
                                .url("https://github.com/fabriciosouza"))
                        .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"))
                );
    }
}
