package com.juan.api.biblioteca.services;

import java.util.List;

import com.juan.api.biblioteca.entities.Autor;

public interface IAutorService {
	
	List<Autor> findAll();
	
	Autor findAutorById(long id);
	
	Autor createAutor(Autor autor);
	
	Autor updateAutor(long id, Autor autor);
	
	void deleteAutorById(long id);

}

