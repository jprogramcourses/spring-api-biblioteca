package com.juan.api.biblioteca.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juan.api.biblioteca.entities.Editorial;
import com.juan.api.biblioteca.repositories.EditorialRepository;

@Service
public class EditorialServiceImpl implements IEditorialService {
	
	@Autowired
	EditorialRepository editorialRepository;

	@Override
	public Optional<Editorial> findById(long id) {
		return editorialRepository.findById(id);
	}

	@Override
	public List<Editorial> findAll() {
		return editorialRepository.findAll();
	}
	
	@Override
	public Editorial createEditorial(Editorial editorial) {
		return editorialRepository.save(editorial);
	}

	@Override
	public Editorial updateEditorial(long id, Editorial editorial) {
		Editorial editorialToUpdate = this.findById(id).orElse(null);
		if(editorialToUpdate != null) {
			editorialToUpdate.setNombre(editorial.getNombre());
			editorialToUpdate.setLibros(editorial.getLibros());
		}
		return editorialRepository.save(editorialToUpdate);
	}

	@Override
	public void deleteEditorialById(long id) {
		editorialRepository.deleteById(id);
	}

}
