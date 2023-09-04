package com.juan.api.biblioteca.services;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juan.api.biblioteca.dto.AutorDto;
import com.juan.api.biblioteca.entities.Autor;
import com.juan.api.biblioteca.mapper.AutorMapper;
import com.juan.api.biblioteca.repositories.AutorRepository;

@Service
public class AutorServiceImpl implements IAutorService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(AutorServiceImpl.class);
	
	@Autowired
	AutorRepository autorRespository;
	
	@Autowired
	private AutorMapper autorMapper;

	@Override
	public List<AutorDto> findAll() {
		List<Autor> autores = autorRespository.findAll();
		if(!CollectionUtils.isEmpty(autores)) {
			return autores.stream().map(aut -> autorMapper.autorToAutorDto(aut)).collect(Collectors.toList());
		}
		return null;
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
