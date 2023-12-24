package com.juan.api.biblioteca.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;

import com.juan.api.biblioteca.entities.Autor;

public class CustomAutorRepositoryImpl implements CustomAutorRepository {

	private EntityManager entityManager;
	
	@Override
	public List<Autor> listAutorByApellido(String apellido) {
		Query query = entityManager.createQuery("SELECT a FROM Autor a WHERE a.apellido like :apellido");
		query.setParameter("apellido", apellido + "%");
		List<Autor> autores = query.getResultList();
		return autores;
	}
	
	@Override
	public Autor getByNombreAndApellido(String nombre, String apellido) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Autor> criteriaQuery = criteriaBuilder.createQuery(Autor.class);
		
		Root<Autor> root = criteriaQuery.from(Autor.class);
		
		ParameterExpression<String> nombreParam = criteriaBuilder.parameter(String.class);
		ParameterExpression<String> apellidoParam = criteriaBuilder.parameter(String.class);
		
		Predicate nombrePredicate = criteriaBuilder.equal(root.get("nombre"), nombreParam);
		Predicate apellidoPredicate = criteriaBuilder.equal(root.get("apellido"), apellidoParam);
		
		criteriaQuery.select(root).where(criteriaBuilder.and(nombrePredicate, apellidoPredicate));
		
		TypedQuery<Autor> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setParameter(nombreParam, nombre);
		typedQuery.setParameter(apellidoParam, apellido);
		
		return typedQuery.getSingleResult();
	}



	

	
}
