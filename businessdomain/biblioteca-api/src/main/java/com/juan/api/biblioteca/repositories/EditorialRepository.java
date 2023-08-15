package com.juan.api.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juan.api.biblioteca.entities.Editorial;

public interface EditorialRepository extends JpaRepository<Editorial, Long> {

}
