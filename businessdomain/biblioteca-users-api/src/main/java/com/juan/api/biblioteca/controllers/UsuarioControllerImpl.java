package com.juan.api.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.api.biblioteca.entities.Usuario;
import com.juan.api.biblioteca.services.IUsuarioService;

@RestController
@RequestMapping(value = "/api/v1")
public class UsuarioControllerImpl implements IUsuarioController {
	
	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping(value = "/usuarios")
	@Override
	public List<Usuario> findUsuarios() {
		return usuarioService.findUsuarios();
	}

	@GetMapping(value = "/usuario/{id}")
	@Override
	public Usuario findUsuarioById(@PathVariable long id) {
		return usuarioService.findUsuarioById(id);
	}

}
