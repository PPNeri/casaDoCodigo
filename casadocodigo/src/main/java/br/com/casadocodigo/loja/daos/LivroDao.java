package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Livro;

public class LivroDao {

	@PersistenceContext
	EntityManager em;

	public void persist(Livro livro) {
		em.persist(livro);
	}

	public void delete(Livro livro) {

	}

	public List<Livro> getLivrosCadastrados() {

		String jpql = " select distinct(l) from Livro l " + " join fetch l.autores";

		return em.createQuery(jpql, Livro.class).getResultList();
	}

}
