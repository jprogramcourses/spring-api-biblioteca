package com.juan.api.biblioteca.services;

import java.util.List;
import java.util.Optional;

import com.juan.api.biblioteca.entities.Editorial;

public interface IEditorialService {
	
	Optional<Editorial> findById(long id);
	
	List<Editorial> findAll();
	
	Editorial createEditorial(Editorial editorial);
	
	Editorial updateEditorial(long id, Editorial editorial);
	
	void deleteEditorialById(long id);

}
