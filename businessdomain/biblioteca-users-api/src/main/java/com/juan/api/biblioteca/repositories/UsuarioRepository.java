package com.juan.api.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juan.api.biblioteca.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
