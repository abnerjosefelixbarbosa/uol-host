package com.org.uol_host_back_end_spring_boot_java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerCofig {
	@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API Oul Host")
                .version("1.0.0")
                .description("Documentação da API Oul Host com a OpenAPI"));
    }
}