package com.juan.api.biblioteca.mapper;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.juan.api.biblioteca.dto.AutorDto;
import com.juan.api.biblioteca.dto.LibroDto;
import com.juan.api.biblioteca.entities.Autor;
import com.juan.api.biblioteca.entities.Libro;

@Mapper(componentModel = "spring")
public interface AutorMapper {
	
	@Mapping(target = "libros", qualifiedByName = {"librosToLibrosDto"})
//	@Mapping(target = "libros", ignore = true)
	AutorDto autorToAutorDto(Autor autor);

	Autor autorDtoToAutor(AutorDto autorDto);
	
	@Named("librosToLibrosDto")
	default Set<LibroDto> librosToLibrosDto(Set<Libro> libros){
		Set<LibroDto> librosDto = new HashSet<>();
		if(!CollectionUtils.isEmpty(libros)) {
			for (Libro libro : libros) {
				LibroDto libDto = new LibroDto();
				libDto.setId(libro.getId());
				libDto.setTitulo(libro.getTitulo());
				libDto.setPaginas(libro.getPaginas());
				libDto.setAnhoEdicion(libro.getAnhoEdicion());
				libDto.setEditorial(libro.getEditorial());
				libDto.setCreatedAt(libro.getCreatedAt());
				libDto.setLastModified(libro.getLastModified());
				
				librosDto.add(libDto);
			}
		}
		return librosDto;
	}

}
