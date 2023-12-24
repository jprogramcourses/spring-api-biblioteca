package com.juan.api.biblioteca.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.juan.api.biblioteca.dto.LibroDto;
import com.juan.api.biblioteca.entities.Libro;

public interface ILibroService {
	
	Libro findById(long id);
	
	List<LibroDto> findAll();
	
	Page<LibroDto> findAll(Pageable pageable);
	
	LibroDto createLibro(LibroDto libro);
	
	Libro updateLibro(long id, Libro libro);
	
	void deleteLibroById(long id);

}
