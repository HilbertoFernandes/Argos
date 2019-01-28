package br.edu.ifpb.argos.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;
import br.edu.ifpb.argos.entity.Objeto;
import br.edu.ifpb.argos.facade.ObjetoController;
import br.edu.ifpb.argos.facade.InformacaoController;

@ManagedBean(name = "objetoBean")
@ViewScoped
public class ObjetoBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Objeto objeto;
	private Integer id = null;
	private String titulo;
	private String descricao;
	private Date data;
	private List<Objeto> objetos;
	private boolean editando = false;
	private String argumento;

	public String salvar() {
		ObjetoController controller = new ObjetoController();
		String proxView = null;

		if (id != null) {
			objeto = controller.buscar(id);
			objeto.setDescricao(descricao);
			controller.atualizar(objeto);
			proxView = "objetos?faces-redirect=true";
		} else {
			try {
				objeto = new Objeto();
				objeto.setTitulo(titulo);
				objeto.setDescricao(descricao);
				objeto.setData(data);
				controller.cadastrar(objeto);
				this.addSuccessMessage("Objeto salvo com sucesso");
				proxView = "objetos?faces-redirect=true";
				objeto = new Objeto();
			} catch (PersistenceException e) {
				this.addErrorMessage("Erro ao tentar salvar o usuário.");
			}
		}
		return proxView;
	}

	public String editar(Objeto objeto) {
		this.titulo = objeto.getTitulo();
		this.descricao = objeto.getDescricao();
		this.data = objeto.getData();
		this.id = objeto.getId();
		this.editando = true;
		return "cadastro?faces-redirect=true&includeViewParams=true";
	}

	public String excluir(Objeto objeto) {
		String proxima_pagina = null;
		ObjetoController controller = new ObjetoController();
		controller.excluir(objeto);
		this.addSuccessMessage("Objeto excluído com sucesso");
		proxima_pagina = "objetos?faces-redirect=true";
		return proxima_pagina;
	}

	public void listarObjetos() {
		ObjetoController controller = new ObjetoController();
		this.objetos = controller.listar();
	}

	public List<Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(List<Objeto> objetos) {
		this.objetos = objetos;
	}

	public String goCadastro() {
		return "/objeto/cadastro?faces-redirect=true";
	}

	public String goObjetos() {
		return "/objeto/objetos?faces-redirect=true";
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
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

	public String getArgumento() {
		return argumento;
	}

	public void setArgumento(String argumento) {
		this.argumento = argumento;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public String pesquisarObjetos() {
		ObjetoController controller = new ObjetoController();
		this.objetos = controller.pesquisar(argumento);
	
		if (objetos.isEmpty())
			this.addErrorMessage("Não há objetos para o argumento informado.");
		return "busca?faces-redirect=true&includeViewParams=true";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
