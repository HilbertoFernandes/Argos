package br.edu.ifpb.argos.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.PersistenceException;
import br.edu.ifpb.argos.entity.Crime;
import br.edu.ifpb.argos.facade.CrimeController;
import br.edu.ifpb.argos.facade.InformacaoController;

@ManagedBean(name = "crimeBean")
@ViewScoped
public class CrimeBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Crime crime;
	private Integer id = null;
	private String titulo;
	private String descricao;
	private Date data;
	private List<Crime> crimes;
	private boolean editando = false;
	private String argumento;

	public String salvar() {
		CrimeController controller = new CrimeController();
		String proxView = null;

		if (id != null) {
			crime = controller.buscar(id);
			crime.setDescricao(descricao);
			controller.atualizar(crime);
			proxView = "crimes?faces-redirect=true";
		} else {
			try {
				crime = new Crime();
				crime.setTitulo(titulo);
				crime.setDescricao(descricao);
				crime.setData(data);
				controller.cadastrar(crime);
				this.addSuccessMessage("Crime salvo com sucesso");
				proxView = "crimes?faces-redirect=true";
				crime = new Crime();
			} catch (PersistenceException e) {
				this.addErrorMessage("Erro ao tentar salvar o usuário.");
			}
		}
		return proxView;
	}

	public String editar(Crime crime) {
		this.titulo = crime.getTitulo();
		this.descricao = crime.getDescricao();
		this.data = crime.getData();
		this.id = crime.getId();
		this.editando = true;
		return "cadastro?faces-redirect=true&includeViewParams=true";
	}

	public String excluir(Crime crime) {
		String proxima_pagina = null;
		CrimeController controller = new CrimeController();
		controller.excluir(crime);
		this.addSuccessMessage("Crime excluído com sucesso");
		proxima_pagina = "crimes?faces-redirect=true";
		return proxima_pagina;
	}

	public void listarCrimes() {
		CrimeController controller = new CrimeController();
		this.crimes = controller.listar();
	}

	public List<Crime> getCrimes() {
		return crimes;
	}

	public void setCrimes(List<Crime> crimes) {
		this.crimes = crimes;
	}

	public String goCadastro() {
		return "/crime/cadastro?faces-redirect=true";
	}

	public String goCrimes() {
		return "/crime/crimes?faces-redirect=true";
	}

	public Crime getCrime() {
		return crime;
	}

	public void setCrime(Crime crime) {
		this.crime = crime;
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

	public String pesquisarCrimes() {
		CrimeController controller = new CrimeController();
		this.crimes = controller.pesquisar(argumento);
	
		if (crimes.isEmpty())
			this.addErrorMessage("Não há crimes para o argumento informado.");
		return "busca?faces-redirect=true&includeViewParams=true";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
