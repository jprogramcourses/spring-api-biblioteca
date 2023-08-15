package com.juan.api.biblioteca.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.api.biblioteca.entities.Autor;
import com.juan.api.biblioteca.repositories.AutorRepository;
import com.juan.api.biblioteca.services.IAutorService;

@RestController
@RequestMapping("/api/v1")
public class AutorController {
	
	@Autowired
	IAutorService autorService;
	
	@GetMapping(value = "/autores")
	public ResponseEntity<?> getAllAutores() {
		List<Autor> listAutores = autorService.findAll();
		if(listAutores.isEmpty()) {
			return new ResponseEntity<String>("No existen autores registrados", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Autor>>(listAutores, HttpStatus.OK);
	}
	
	@GetMapping(value = "/autores/{id}")
	public Autor getAutor(@PathVariable Long id) {
		return autorService.findAutorById(id);
	}
	
	@PostMapping(value = "/autores")
	public Autor createAutor(@RequestBody Autor newAutor) {
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
