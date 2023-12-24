package com.juan.api.biblioteca.services;

import java.util.List;
import java.util.Optional;

import com.juan.api.biblioteca.entities.Usuario;

public interface IUsuarioService {
	
	List<Usuario> findUsuarios();
	
	Usuario findUsuarioById(long id);
	
	Optional<Usuario> findUsuarioByEmail(String email);

}
