package com.juan.api.biblioteca.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.juan.api.biblioteca.entities.Autor;
import com.juan.api.biblioteca.entities.Editorial;
import com.juan.api.biblioteca.entities.Genero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibroDto {
	
	private long id;
	private String titulo;
	private int anhoEdicion;
	private int paginas;
	private LocalDateTime createdAt;
	private LocalDateTime lastModified;
	private Genero genero;
	private Editorial editorial;
	private Set<Autor> autores = new HashSet<>();

}
