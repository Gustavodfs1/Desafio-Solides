package com.solides.desafio.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Desafio Técnico")
                        .description("Desafio técnico de Gustavo Faria")
                        .version("1.0")
                        .contact(contact()));
    }

    private Contact contact() {
        return new Contact()
                .name("Gustavo Faria")
                .url("https://github.com/gustavodfs1")
                .email("gustavodfs@gmail.com");
    }
}
