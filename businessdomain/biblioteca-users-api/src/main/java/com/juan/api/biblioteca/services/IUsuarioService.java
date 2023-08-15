package com.juan.api.biblioteca.services;

import java.util.List;

import com.juan.api.biblioteca.entities.Usuario;

public interface IUsuarioService {
	
	List<Usuario> findUsuarios();
	
	Usuario findUsuarioById(long id);

}
