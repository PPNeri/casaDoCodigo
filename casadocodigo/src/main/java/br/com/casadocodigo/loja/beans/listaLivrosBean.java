package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Livro;

//Named+RequestScoped
@Model
public class listaLivrosBean {

	@Inject
	LivroDao livroDao;

	private List<Livro> livros = new ArrayList<>();

	public List<Livro> populaListaDeLivros() {
		this.livros = livroDao.getLivrosCadastrados();
		return livros;
	}

}
