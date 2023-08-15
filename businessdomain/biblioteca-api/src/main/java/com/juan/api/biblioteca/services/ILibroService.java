package com.juan.api.biblioteca.services;

import java.util.List;

import com.juan.api.biblioteca.entities.Libro;

public interface ILibroService {
	
	Libro findById(long id);
	
	List<Libro> findAll();
	
	Libro createLibro(Libro libro);
	
	Libro updateLibro(long id, Libro libro);
	
	void deleteLibroById(long id);

}
