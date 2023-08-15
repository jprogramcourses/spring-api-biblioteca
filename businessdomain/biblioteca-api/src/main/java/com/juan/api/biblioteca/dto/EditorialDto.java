package com.juan.api.biblioteca.dto;

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
public class EditorialDto {
	
	private long id;
	private String nombre;
	private Set<Libro> libros = new HashSet<>();

}
