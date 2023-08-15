package com.juan.api.biblioteca.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().components(new Components())
				.info(new Info()
						.title("Biblioteca API")
				.description("Permite recuperar los datos de autores y libros disponibles")
				.version("1.0.0"));
	}

}
