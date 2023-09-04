package com.juan.api.biblioteca.mapper;

import org.mapstruct.Mapper;

import com.juan.api.biblioteca.dto.LibroDto;
import com.juan.api.biblioteca.entities.Libro;

@Mapper(componentModel = "spring", uses = {EditorialMapper.class})
public interface LibroMapper {

	LibroDto libroToLibroDto(Libro libro);
	
	Libro libroDtoToLibro(LibroDto libroDto);
	
}
