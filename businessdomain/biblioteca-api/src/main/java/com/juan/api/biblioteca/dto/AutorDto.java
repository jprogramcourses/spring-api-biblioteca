package com.juan.api.biblioteca.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
	
	@NotBlank
	@Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
	private String nombre;
	
	private String apellidos;
	private String nacionalidad;
	private LocalDateTime createdAt;
	private LocalDateTime lastModified;
	private Set<LibroDto> libros = new HashSet<>();

}
