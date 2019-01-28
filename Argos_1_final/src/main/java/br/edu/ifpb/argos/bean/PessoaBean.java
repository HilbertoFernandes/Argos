package br.edu.ifpb.argos.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.PersistenceException;

import br.edu.ifpb.argos.entity.Investigacao;
import br.edu.ifpb.argos.entity.Pessoa;
import br.edu.ifpb.argos.facade.PessoaController;


@ManagedBean(name = "pessoaBean")
@SessionScoped

public class PessoaBean extends GenericBean {
	private Integer id = null;
	private String nome;
	private String apelido;
	private String historico;
	private Byte[] foto;
	private List<Investigacao> investigacoes;
	
	private Pessoa pessoa;
	private List<Pessoa> pessoas;
	
	
	// Metodo para criar uma nova Pessoa

	public String salvar() {
		PessoaController controller = new PessoaController();
		String proxView = null;
		
		try {
			pessoa = new Pessoa();
			pessoa.setNome(nome);
			pessoa.setApelido(apelido);
			pessoa.setHistorico(historico);
			pessoa.setFoto(getFoto());
			pessoa.setInvestigacoes(getInvestigacoes());
			controller.cadastrar(pessoa);
			this.addSuccessMessage("Pessoa criada com sucesso!");
			proxView = "pessoas?faces-redirect=true";
			pessoa = new Pessoa();
		}
		catch (PersistenceException e) {
			this.addErrorMessage("Erro ao tentar criar pessoa.");
		}
		return proxView;
	}
	
	
	//implementado por Hilberto
	public String goCadastroPessoa() {
		return "/pessoa/cadastroPessoa?faces-redirect=true";
	}
	
	// Metodo para atualizar dados de Pessoa
	
	public String editar(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.apelido = pessoa.getApelido();
		this.historico = pessoa.getHistorico();
		this.foto = pessoa.getFoto();
		return "cadastroPessoa?faces-redirect=true&includeViewParams=true";
	}
	
	// Metodo para excluir Pessoa
	
	public String excluir(Pessoa pessoa) {
		String proxima_pagina = null;
		
		PessoaController controller = new PessoaController();
		controller.excluir(pessoa);
		this.addSuccessMessage("Pessoa excluida com sucesso!");
		
		proxima_pagina = "pessoas?faces-redirect-true";
		return proxima_pagina;
	}
	
	// Metodo para buscar uma Pessoa pelo nome
	
	public String pesquisarPessoa() {
		PessoaController controller = new PessoaController();
		this.pessoa = controller.buscar(nome);
		
		if (pessoa == null) {
			this.addErrorMessage("Essa pessoa nao existe!");
		}
		return "buscaPessoa?faces-redirect=true&includeViewParams=true";
	}
	
	public void listarPessoas() {
		PessoaController controller = new PessoaController();
		this.pessoas = controller.listar();
	}
	
	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getApelido() {
		return apelido;
	}
	
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	
	public String getHistorico() {
		return historico;
	}
	
	public void setHistorico(String historico) {
		this.historico = historico;
	}
	
	public Byte[] getFoto() {
		return foto;
	}
	
	public void setFoto(Byte[] foto) {
		this.foto = foto;
	}
	
	public List<Investigacao> getInvestigacoes() {
		return investigacoes;
	}

	public void setInvestigacoes(List<Investigacao> investigacoes) {
		this.investigacoes = investigacoes;
	}
	
	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}
}