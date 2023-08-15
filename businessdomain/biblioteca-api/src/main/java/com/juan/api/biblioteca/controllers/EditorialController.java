package com.juan.api.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.juan.api.biblioteca.entities.Editorial;
import com.juan.api.biblioteca.services.IEditorialService;

@RestController
@RequestMapping(value = "/api/v1")
public class EditorialController {
	
	@Autowired
	private IEditorialService editorialService;
	
	@GetMapping(value = "/editoriales")
	public ResponseEntity<?> getEditoriales(){
		List<Editorial> editoriales = editorialService.findAll();
		if(editoriales.isEmpty()) {
			return new ResponseEntity<String>("No existen editoriales registradas", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Editorial>>(editoriales, HttpStatus.OK);
	}
	
	@GetMapping(value = "/editoriales/{id}")
	public ResponseEntity<?> getEditorial(@PathVariable long id) {
		Editorial editorial = editorialService.findById(id).orElse(null);
		if(editorial == null) {
			// throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Editorial no encontrada");
			return new ResponseEntity<Editorial>(new Editorial(), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Editorial>(editorial, HttpStatus.OK);
	}
	
	@PostMapping(value = "/editoriales")
	public Editorial createEditorial(@RequestBody Editorial editorial) {
		return editorialService.createEditorial(editorial);
	}
	
	@PutMapping(value = "/editoriales/{id}")
	public Editorial updateEditorial(@PathVariable long id, @RequestBody Editorial editorial) {
		return editorialService.updateEditorial(id, editorial);
	}

}
