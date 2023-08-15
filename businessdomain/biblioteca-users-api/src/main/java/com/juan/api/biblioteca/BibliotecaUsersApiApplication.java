package com.juan.api.biblioteca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BibliotecaUsersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaUsersApiApplication.class, args);
	}

}
