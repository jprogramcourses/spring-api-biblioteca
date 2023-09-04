package com.juan.api.biblioteca.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.juan.api.biblioteca.entities.Libro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AutorDto {
	
	private long id;
	private String nombre;
	private String apellidos;
	private String nacionalidad;
	private LocalDateTime createdAt;
	private LocalDateTime lastModified;
	private Set<LibroDto> libros = new HashSet<>();

}
