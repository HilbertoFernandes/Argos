package br.edu.ifpb.argos.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.edu.ifpb.argos.entity.Crime;
import br.edu.ifpb.argos.entity.Investigacao;
import br.edu.ifpb.argos.facade.CrimeController;
import br.edu.ifpb.argos.facade.InvestigacaoController;

@ManagedBean(name = "crimeBean")
@ViewScoped
public class CrimeBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Crime crime;
	private Integer id = null;
	private String titulo;
	private String historico;
	private Date data;
	private List<Crime> crimes;
	private boolean editando = false;
	private String argumento;
	@ManagedProperty("#{investigacaoBean}")
	private InvestigacaoBean investigacaoBean;

	public String salvar() throws IOException {
		String proxView = null;
		CrimeController controller = new CrimeController();
		crime = new Crime();
		if (id != null) {
			editando = true;
			crime = controller.buscar(id);
			crime.setTitulo(titulo);
			crime.setHistorico(historico);
			controller.atualizar(crime);
			proxView = "lista?faces-redirect=true";
		} else {
			crime.setData(data);
			crime.setTitulo(titulo);
			crime.setHistorico(historico);

			if (investigacaoBean.isComesHomeInvestigacao()) {
				controller.cadastrar(crime);
				InvestigacaoController ic = new InvestigacaoController();
				Investigacao i = ic.buscar(investigacaoBean.getInvestigacao().getId());
				i.getCrimes().add(crime);
				ic.atualizar(i);
				investigacaoBean.setComesHomeInvestigacao(false);
				proxView = "/investigacao/home?faces-redirect=true&includeViewParams=true";
			} else {
				proxView = "/usuario/home?faces-redirect=true&includeViewParams=true";
				controller.cadastrar(crime);
			}
		}
		this.addSuccessMessage("Sucesso!");
		crime = new Crime();
		return proxView;
	}

	public String editar(Crime crime) {
		this.titulo = crime.getTitulo();
		this.historico = crime.getHistorico();
		this.id = crime.getId();
		this.editando = true;
		return "cadastro?faces-redirect=true&includeViewParams=true";
	}

	public String excluir(Crime crime) {
		String proxima_pagina = null;
		CrimeController controller = new CrimeController();
		controller.excluir(crime);
		this.addSuccessMessage("Crime excluído com sucesso");
		proxima_pagina = "lista?faces-redirect=true";
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

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String descricao) {
		this.historico = descricao;
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

	public InvestigacaoBean getInvestigacaoBean() {
		return investigacaoBean;
	}

	public void setInvestigacaoBean(InvestigacaoBean investigacaoBean) {
		this.investigacaoBean = investigacaoBean;
	}

}
