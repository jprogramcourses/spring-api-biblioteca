package com.juan.api.biblioteca;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.juan.api.biblioteca.entities.Libro;
import com.juan.api.biblioteca.repositories.LibroRepository;

@DataJpaTest
public class SpringBootJpaTestSlice {
	
	@Autowired
	LibroRepository libroRepository;
	
	@Test
	void testJpaTestSplice() {
		Libro libro = new Libro();
		libro.setTitulo("Bóvedas de Acero");
		
		libroRepository.save(libro);
		
		long count = libroRepository.findAll().size();
		
		assertTrue(count == 1);
	}

}
