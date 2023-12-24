package com.juan.api.biblioteca.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	
	@NotBlank
	@Size(min = 3, message = "El título debe tener al menos 3 caracteres")
	private String titulo;
	private int anhoEdicion;
	private int paginas;
	private LocalDateTime createdAt;
	private LocalDateTime lastModified;
	private Genero genero;
	private Editorial editorial;
	private String descripcion;
	private String portada;
	private Set<Autor> autores = new HashSet<>();

}
