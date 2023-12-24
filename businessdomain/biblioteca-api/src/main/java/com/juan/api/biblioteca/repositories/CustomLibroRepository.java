package com.juan.api.biblioteca.repositories;

import com.juan.api.biblioteca.entities.Libro;

public interface CustomLibroRepository {
	
	Libro getById(Long id);
	
	Libro findByTitulo(String titulo);

}
