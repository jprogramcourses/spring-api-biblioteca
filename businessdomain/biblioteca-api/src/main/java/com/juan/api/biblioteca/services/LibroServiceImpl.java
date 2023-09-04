package com.juan.api.biblioteca.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juan.api.biblioteca.dto.LibroDto;
import com.juan.api.biblioteca.entities.Libro;
import com.juan.api.biblioteca.mapper.LibroMapper;
import com.juan.api.biblioteca.repositories.LibroRepository;

@Service
public class LibroServiceImpl implements ILibroService {
	
	@Autowired
	LibroRepository libroRepository;
	
	@Autowired
	private LibroMapper libroMapper;

	@Override
	public Libro findById(long id) {
		return libroRepository.getReferenceById(id);
	}

	@Override
	public List<LibroDto> findAll() {
		List<Libro> libros = libroRepository.findAll();
		return libros.stream().map(lib -> libroMapper.libroToLibroDto(lib)).collect(Collectors.toList());
	}
	
	@Override
	public Libro createLibro(Libro libro) {
		return libroRepository.save(libro);
	}

	@Override
	public Libro updateLibro(long id, Libro libro) {
		Libro libroToUpdate = this.findById(id);
		if(libroToUpdate != null) {
			libroToUpdate.setTitulo(libro.getTitulo());
			libroToUpdate.setGenero(libro.getGenero());
			libroToUpdate.setAnhoEdicion(libro.getAnhoEdicion());
			libroToUpdate.setPaginas(libro.getPaginas());
			libroToUpdate.setEditorial(libro.getEditorial());
			libroToUpdate.setAutores(libro.getAutores());
		}
		
		return libroRepository.save(libroToUpdate);
	}

	@Override
	public void deleteLibroById(long id) {
		libroRepository.deleteById(id);
	}

}
