package com.juan.api.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juan.api.biblioteca.entities.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
