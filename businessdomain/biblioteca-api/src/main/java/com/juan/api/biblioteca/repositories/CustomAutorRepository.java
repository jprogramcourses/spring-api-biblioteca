package com.juan.api.biblioteca.repositories;

import java.util.List;

import com.juan.api.biblioteca.entities.Autor;

public interface CustomAutorRepository {
	
	List<Autor> listAutorByApellido(String apellido);

	Autor getByNombreAndApellido(String nombre, String apellido);
	
}
