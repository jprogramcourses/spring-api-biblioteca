package com.juan.api.biblioteca.dto;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
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
	
	@JsonProperty("_id")
	private long id;
	
	@NotBlank
	@Size(min = 3, message = "El título debe tener al menos 3 caracteres")
	@JsonProperty("_titulo")
	private String titulo;
	@JsonProperty("_anhoPublicacion")
	private int anhoEdicion;
	@JsonProperty("_paginas")
	private int paginas;
	@JsonProperty("_createdAt")
	private LocalDateTime createdAt;
	@JsonProperty("_lastModified")
	private LocalDateTime lastModified;
	@JsonProperty("_genero")
	private Genero genero;
	@JsonProperty("_editorial")
	private Editorial editorial;
	@JsonProperty("_descripcion")
	private String descripcion;
	@JsonProperty("_portada")
	private String portada;
	@JsonProperty("_autores")
	private Set<Autor> autores = new HashSet<>();

}
