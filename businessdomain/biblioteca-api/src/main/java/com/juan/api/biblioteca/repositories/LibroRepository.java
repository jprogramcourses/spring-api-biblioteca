package com.juan.api.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juan.api.biblioteca.entities.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
