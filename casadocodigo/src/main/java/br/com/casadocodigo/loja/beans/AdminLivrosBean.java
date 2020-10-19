package br.com.casadocodigo.loja.beans;

import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import javax.transaction.Transactional;

import org.jboss.logging.Logger;

import br.com.casadocodigo.loja.daos.AutorDao;
import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.infra.FileSaver;
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

	private Part capaLivro;

	@Transactional
	public String save() throws IOException {

//		for (Long id : autoresId) {
//			Autor que já existe, porem deve ser instanciado para salvar no Livro
//			livro.getAutores().add(new Autor(id));
//		}
// 		Reset nos campos
//		this.livro = new Livro();
//		this.autoresId = new ArrayList<>();
// 		Adicionando as mensagens de Sucesso após redirecionamento a Pagina

		livroDao.persist(livro);
		FileSaver fileSaver = new FileSaver();

		livro.setCapaLivroPath(fileSaver.writeFile(capaLivro, "casaDoCodigo\\capasLivros\\"));

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

	public Part getCapaLivro() {
		return capaLivro;
	}

	public void setCapaLivro(Part capaLivro) {
		this.capaLivro = capaLivro;
	}

	public List<Autor> listarAutores() {
		return autorDao.getAutoresCadastrados();
	}

}
