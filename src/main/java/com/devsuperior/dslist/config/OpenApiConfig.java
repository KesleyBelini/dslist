package com.devsuperior.dslist.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DSList API")
                        .version("1.0.0")
                        .description("API REST para gerenciamento de listas de jogos desenvolvida com Spring Boot")
                        .contact(new Contact()
                                .name("DevSuperior")
                                .email("contato@devsuperior.com")
                                .url("https://devsuperior.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")));
    }
}
