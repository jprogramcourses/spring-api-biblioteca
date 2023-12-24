package com.juan.api.biblioteca.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juan.api.biblioteca.entities.Usuario;
import com.juan.api.biblioteca.services.IUsuarioService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api/v1")
public class UsuarioControllerImpl implements IUsuarioController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioControllerImpl.class);
	
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

	@GetMapping(value = "/usuario/email/{email}")
	@Override
	public ResponseEntity<?> findUsuarioByEmail(@PathVariable String email) {
		Optional<Usuario> optUsuarioOptional;
		Map<String, Object> response = new HashMap<>();
		if(!StringUtils.isBlank(email)) {
			try {
				optUsuarioOptional = usuarioService.findUsuarioByEmail(email);
				if(optUsuarioOptional.isPresent()) {
					return new ResponseEntity<Usuario>(optUsuarioOptional.get(), HttpStatus.OK);
				}
				response.put("message", "El usuario con email " + email + " no existe");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}catch(DataAccessException e) {
				response.put("message", "Error en la recuperación de los datos");
				response.put("error", e.getMessage());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		response.put("message", "El email enviado no es correcto");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping(value = "/usuario", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario){
		Map<String, Object> response = new HashMap<>();
		if(usuario == null) {
			response.put("message", "El usuario enviado no es válido");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		response.put("message", "Usuario creado con éxito");
		response.put("usuario", new Usuario());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
