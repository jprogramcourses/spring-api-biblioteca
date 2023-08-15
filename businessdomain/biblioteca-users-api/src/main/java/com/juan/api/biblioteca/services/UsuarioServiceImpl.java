package com.juan.api.biblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juan.api.biblioteca.entities.Usuario;
import com.juan.api.biblioteca.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public List<Usuario> findUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario findUsuarioById(long id) {
		return usuarioRepository.findById(id).orElse(null);
	}

}
