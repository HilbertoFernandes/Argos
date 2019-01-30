package br.edu.ifpb.argos.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import br.edu.ifpb.argos.entity.Informacao;
import br.edu.ifpb.argos.facade.InformacaoController;
import br.edu.ifpb.argos.facade.PessoaController;

@ManagedBean(name = "informacaoBean")
@ViewScoped
public class InformacaoBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Informacao informacao;
	private Integer id = null;
	private String titulo;
	private String descricao;
	private List<Informacao> informacoes;

	public String salvar() {
		InformacaoController controller = new InformacaoController();
		String proxView = null;

		if (id != null) {
			informacao = controller.buscar(id);
			informacao.setTitulo(titulo);
			informacao.setDescricao(descricao);
			controller.atualizar(informacao);
			proxView = "/usuario/home?faces-redirect=true";
		} else {
			try {
				informacao = new Informacao();
				informacao.setTitulo(titulo);
				informacao.setDescricao(descricao);
				controller.cadastrar(informacao);
				this.addSuccessMessage("Informacao salva com sucesso");
				proxView = "/usuario/home?faces-redirect=true";
				informacao = new Informacao();
			} catch (PersistenceException e) {
				this.addErrorMessage("Erro ao tentar salvar o usuário.");
			}
		}
		return proxView;
	}

	public String editar(Informacao informacao) {
		this.titulo = informacao.getTitulo();
		this.descricao = informacao.getDescricao();
		this.id = informacao.getId();
		return "cadastro?faces-redirect=true&includeViewParams=true";
	}

	public String excluir(Informacao informacao) {
		String proxima_pagina = null;
		InformacaoController controller = new InformacaoController();
		controller.excluir(informacao);
		this.addSuccessMessage("Informacao excluído com sucesso");
		proxima_pagina = "informacaos?faces-redirect=true";
		return proxima_pagina;
	}

	public void listarInformacoes() {
		InformacaoController controller = new InformacaoController();
		this.informacoes = controller.listar();
	}

	public String goHome() {
		return "/usuario/home?faces-redirect=true";
	}

	public Informacao getInformacao() {
		return informacao;
	}

	public void setInformacao(Informacao informacao) {
		this.informacao = informacao;
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

	public List<Informacao> getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(List<Informacao> informacoes) {
		this.informacoes = informacoes;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
