package com.juan.api.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BibliotecaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaApiApplication.class, args);
	}

}
