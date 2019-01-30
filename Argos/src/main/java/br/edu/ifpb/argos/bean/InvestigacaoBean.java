package br.edu.ifpb.argos.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import br.edu.ifpb.argos.entity.Crime;
import br.edu.ifpb.argos.entity.Fato;
import br.edu.ifpb.argos.entity.Informacao;
import br.edu.ifpb.argos.entity.Investigacao;
import br.edu.ifpb.argos.entity.Local;
import br.edu.ifpb.argos.entity.Objeto;
import br.edu.ifpb.argos.entity.Pessoa;
import br.edu.ifpb.argos.facade.InvestigacaoController;

@ManagedBean(name = "investigacaoBean")
@ViewScoped
public class InvestigacaoBean extends GenericBean {
	private Integer id = null;
	private String titulo;
	private String descricao;
	private List<Crime> crimes;
	private List<Local> locais;
	private List<Fato> fatos;
	private List<Objeto> objetos;
	private List<Pessoa> pessoas;
	private List<Informacao> informacoes;
	private Investigacao investigacao;
	private List<Investigacao> investigacoes;

	public String salvar() {
		InvestigacaoController controller = new InvestigacaoController();
		String proxView = null;

		if (id != null) {
			investigacao = controller.buscar(id);
			investigacao.setDescricao(titulo);
			investigacao.setDescricao(descricao);
			controller.atualizar(investigacao);
			this.investigacao = controller.buscar(id);
			return "home?faces-redirect=true&includeViewParams=true";
		} else {
			try {
				investigacao = new Investigacao();
				investigacao.setTitulo(titulo);
				investigacao.setDescricao(descricao);
				controller.cadastrar(investigacao);
				this.addSuccessMessage("investigacao salva com sucesso");
				proxView = "/usuario/home?faces-redirect=true";
			} catch (PersistenceException e) {
				this.addErrorMessage("Erro ao tentar salvar o investigação.");
			}
		}
		return proxView;
	}

	public String excluir(Investigacao investigacao) {
		String proxView = null;
		InvestigacaoController controller = new InvestigacaoController();
		controller.excluir(investigacao);
		this.addSuccessMessage("Investigação excluída com sucesso");
		proxView = "/usuario/home?faces-redirect=true";
		return proxView;
	}

	public String editar(Investigacao investigacao) {
		this.titulo = investigacao.getTitulo();
		this.descricao = investigacao.getDescricao();
		this.id = investigacao.getId();
		System.out.println(investigacao.getId());
		System.out.println(investigacao.getTitulo());
		return "cadastro?faces-redirect=true&includeViewParams=true";
	}

	public void listarInvestigacoes() {
		InvestigacaoController controller = new InvestigacaoController();
		this.investigacoes = controller.listar();
	}

	public String goHome() {
		return "/usuario/home?faces-redirect=true";
	}

	public String goHomeInvestigacao(Investigacao investigacao) {
		InvestigacaoController controller = new InvestigacaoController();
		this.investigacao = controller.buscar(investigacao.getId());
		return "/investigacao/home?faces-redirect=true&includeViewParams=true";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Crime> getCrimes() {
		return crimes;
	}

	public void setCrimes(List<Crime> crimes) {
		this.crimes = crimes;
	}

	public List<Local> getLocais() {
		return locais;
	}

	public void setLocais(List<Local> locais) {
		this.locais = locais;
	}

	public List<Fato> getFatos() {
		return fatos;
	}

	public void setFatos(List<Fato> fatos) {
		this.fatos = fatos;
	}

	public List<Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(List<Objeto> objetos) {
		this.objetos = objetos;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Informacao> getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(List<Informacao> informacoes) {
		this.informacoes = informacoes;
	}

	public Investigacao getInvestigacao() {
		return investigacao;
	}

	public void setInvestigacao(Investigacao investigacao) {
		this.investigacao = investigacao;
	}

	public List<Investigacao> getInvestigacoes() {
		return investigacoes;
	}

	public void setInvestigacoes(List<Investigacao> investigacoes) {
		this.investigacoes = investigacoes;
	}

}