package br.edu.ifpb.argos.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;

import br.edu.ifpb.argos.entity.Informacao;
import br.edu.ifpb.argos.facade.InformacaoController;

@ManagedBean(name = "informacaoBean")
@ViewScoped
public class InformacaoBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Informacao informacao;
	private Integer id = null;
	private String titulo;
	private String descricao;
	private Date data;
	private List<Informacao> informacoes;
	private boolean editando = false;
	private String argumento;

	public String salvar() {
		InformacaoController controller = new InformacaoController();
		String proxView = null;

		if (id != null) {
			informacao = controller.buscar(id);
			informacao.setTitulo(titulo);
			informacao.setDescricao(descricao);
			informacao.setData(data);
			controller.atualizar(informacao);
			proxView = "informacoes?faces-redirect=true";
		} else {
			try {
				informacao = new Informacao();
				informacao.setTitulo(titulo);
				informacao.setDescricao(descricao);
				informacao.setData(data);
				controller.cadastrar(informacao);
				this.addSuccessMessage("Informacao salva com sucesso");
				proxView = "informacoes?faces-redirect=true";
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
		this.data = informacao.getData();
		this.id = informacao.getId();
		this.editando = true;
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

	public String pesquisarInformacao() {
		InformacaoController controller = new InformacaoController();
		this.informacoes = controller.pesquisar(argumento);

		if (informacoes.isEmpty())
			this.addErrorMessage("Não há informacoes para o argumento informado.");
		return "busca?faces-redirect=true&includeViewParams=true";
	}

	public List<Informacao> getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(List<Informacao> informacoes) {
		this.informacoes = informacoes;
	}

	public String goCadastro() {
		return "/informacao/cadastro?faces-redirect=true";
	}

	public String goInformacoes() {
		return "/informacao/informacoes?faces-redirect=true";
	}

	public Informacao getInformacao() {
		return informacao;
	}

	public void setInformacao(Informacao Informacao) {
		this.informacao = Informacao;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public String getArgumento() {
		return argumento;
	}

	public void setArgumento(String argumento) {
		this.argumento = argumento;
	}

	public void listarInformacoes() {
		InformacaoController controller = new InformacaoController();
		this.informacoes = controller.listar();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
