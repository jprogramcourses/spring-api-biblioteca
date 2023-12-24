package com.juan.api.biblioteca;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.juan.api.biblioteca.repositories.LibroRepository;

@SpringBootTest
class BibliotecaApiApplicationTests {

	@Autowired
	LibroRepository libroRepository;
	
	
	
	@Test
	void contextLoads() {
	}

}
