package com.juan.api.biblioteca.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.juan.api.biblioteca.entities.Usuario;

public interface IUsuarioController {
	
	List<Usuario> findUsuarios();
	
	Usuario findUsuarioById(long id);
	
	ResponseEntity<?> findUsuarioByEmail(String email);

}
