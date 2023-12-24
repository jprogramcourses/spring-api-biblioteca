package com.juan.api.biblioteca.services;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(readOnly = true)
	public List<LibroDto> findAll() {
		List<Libro> libros = libroRepository.findAll();
		return libros.stream().map(lib -> libroMapper.libroToLibroDto(lib)).collect(Collectors.toList());
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<LibroDto> findAll(Pageable pageable) {
		Page<Libro> pageLibro = libroRepository.findAll(pageable);
		Page<LibroDto> pageLibroDto = pageLibro.map(lib -> libroMapper.libroToLibroDto(lib));
		return pageLibroDto;
	}
	
	@Override
	public LibroDto createLibro(LibroDto libroDto) {
		
		if(libroDto != null) {
			Libro libro = libroMapper.libroDtoToLibro(libroDto);
			return libroMapper.libroToLibroDto(libroRepository.save(libro));
		}
		
		return null;
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
