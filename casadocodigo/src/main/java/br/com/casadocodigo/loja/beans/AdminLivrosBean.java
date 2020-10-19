package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import br.com.casadocodigo.loja.daos.AutorDao;
import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Autor;
import br.com.casadocodigo.loja.models.Livro;

// @ManagedBean JSF
@Named // CDI
@RequestScoped
public class AdminLivrosBean {

	Logger logger = Logger.getLogger(AdminLivrosBean.class);

	private Livro livro = new Livro();

//	private List<Long> autoresId = new ArrayList<>();

	@Inject
	private LivroDao livroDao;
	@Inject
	private AutorDao autorDao;
	@Inject
	private FacesContext context;

	@Transactional
	public String save() {

//		for (Long id : autoresId) {
//			Autor que já existe, porem deve ser instanciado para salvar no Livro
//			livro.getAutores().add(new Autor(id));
//		}

		livroDao.persist(livro);
// 		Reset nos campos
//		this.livro = new Livro();
//		this.autoresId = new ArrayList<>();

		// Adicionando as mensagens de Sucesso após redirecionamento a Pagina

		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Livro Cadatrado Com Sucesso !!"));

		return redirectToPage("/livros/tabelaLivros");

	}

	public String redirectToPage(String page) {
		return page + "?faces-redirect=true";
	}

	public void excluir() {
		livroDao.delete(livro);
		System.out.println("Livro excluido");
		Logger.getLogger(livro + "excluido");
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

//	public List<Long> getAutoresId() {
//		return autoresId;
//	}
//
//	public void setAutoresId(List<Long> autoresId) {
//		this.autoresId = autoresId;
//	}

	public List<Autor> listarAutores() {
		return autorDao.getAutoresCadastrados();
	}

}
