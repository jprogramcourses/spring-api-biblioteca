package com.juan.api.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juan.api.biblioteca.entities.Autor;
import com.juan.api.biblioteca.repositories.AutorRepository;

@Service
public class AutorServiceImpl implements IAutorService {
	
	@Autowired
	AutorRepository autorRespository;

	@Override
	public List<Autor> findAll() {
		return autorRespository.findAll();
	}

	@Override
	public Autor findAutorById(long id) {
		return autorRespository.getReferenceById(id);
	}

	@Override
	public Autor createAutor(Autor autor) {
		return autorRespository.save(autor);
	}
	
	@Override
	public Autor updateAutor(long id, Autor updateAutor) {
		Autor autorToUpdate = this.findAutorById(id);
		
		if(autorToUpdate != null) {
			autorToUpdate.setNombre(updateAutor.getNombre());
			autorToUpdate.setApellidos(updateAutor.getApellidos());
			autorToUpdate.setNacionalidad(updateAutor.getNacionalidad());
			autorToUpdate.setLibros(updateAutor.getLibros());
		}
		return autorRespository.save(autorToUpdate);
	}

	@Override
	public void deleteAutorById(long id) {
		autorRespository.deleteById(id);
	}

}
