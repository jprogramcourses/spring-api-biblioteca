package com.juan.api.biblioteca.controllers;

import java.util.List;

import com.juan.api.biblioteca.entities.Usuario;

public interface IUsuarioController {
	
	List<Usuario> findUsuarios();
	
	Usuario findUsuarioById(long id);

}
