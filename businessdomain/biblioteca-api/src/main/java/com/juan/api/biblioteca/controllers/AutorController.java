package com.juan.api.biblioteca.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.api.biblioteca.dto.AutorDto;
import com.juan.api.biblioteca.entities.Autor;
import com.juan.api.biblioteca.services.IAutorService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class AutorController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(AutorController.class);
	
	@Autowired
	IAutorService autorService;
	
	@GetMapping(value = "/autores")
	public ResponseEntity<?> getAllAutores() {
		List<AutorDto> listAutores = null;
		try {
			listAutores = autorService.findAll();
		}catch(Exception ex) {
			LOGGER.info("Error en la recuperación de autores");
			ex.printStackTrace();
		}
		if(listAutores.isEmpty()) {
			return new ResponseEntity<String>("No existen autores registrados", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AutorDto>>(listAutores, HttpStatus.OK);
	}
	
	@GetMapping(value = "/autores/{id}")
	public Autor getAutor(@PathVariable Long id) {
		return autorService.findAutorById(id);
	}
	
	@PostMapping(value = "/autores", consumes = {"application/json"})
	public Autor createAutor(@RequestBody Autor newAutor) {
		System.out.println("Crear nuevo autor");
		return autorService.createAutor(newAutor);
	}
	
	@PutMapping(value = "/autores/{id}")
	public Autor updateAutor(@PathVariable long id, @RequestBody Autor updateAutor) {
		return autorService.updateAutor(id, updateAutor);
	}
	
	@DeleteMapping(value = "/autores/{id}")
	public ResponseEntity<?> deleteAutor(@PathVariable long id){
		return new ResponseEntity<String>("Autor eliminado", HttpStatus.NO_CONTENT);
	}

}
