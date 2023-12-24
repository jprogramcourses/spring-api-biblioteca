package com.juan.api.biblioteca.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import com.juan.api.biblioteca.entities.Libro;

public class CustomLibroRepositoryImpl implements CustomLibroRepository {
	
	private final EntityManagerFactory emf;
	
	public CustomLibroRepositoryImpl(EntityManagerFactory emf) {
		this.emf = emf;
	}
	
	@Override
	public Libro getById(Long id) {
		return getEntityManager().find(Libro.class, id);
	}

	@Override
	public Libro findByTitulo(String titulo) {
		
		TypedQuery<Libro> typedQuery = getEntityManager().createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo", Libro.class);
		typedQuery.setParameter("titulo", titulo);
		
		Libro libro = typedQuery.getSingleResult();
		
		return libro;
	}
	
	private EntityManager getEntityManager() {
		return emf.createEntityManager();
	}


}
