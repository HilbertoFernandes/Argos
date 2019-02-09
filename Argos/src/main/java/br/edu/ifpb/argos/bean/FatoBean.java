package br.edu.ifpb.argos.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import br.edu.ifpb.argos.entity.Fato;
import br.edu.ifpb.argos.entity.Investigacao;
import br.edu.ifpb.argos.facade.FatoController;
import br.edu.ifpb.argos.facade.InvestigacaoController;

@ManagedBean(name = "fatoBean")
@ViewScoped
public class FatoBean extends GenericBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Fato fato;
	private Integer id = null;
	private String titulo;
	private String descricao;
	private Date data;
	private List<Fato> fatos;
	private boolean editando = false;
	private String argumento;
	@ManagedProperty("#{investigacaoBean}")
	private InvestigacaoBean investigacaoBean;

	public String salvar() throws IOException {
		String proxView = null;
		FatoController controller = new FatoController();
		fato = new Fato();
		if (id != null) {
			editando = true;
			fato = controller.buscar(id);
			fato.setTitulo(titulo);
			fato.setDescricao(descricao);
			controller.atualizar(fato);
			proxView = "lista?faces-redirect=true";
		} else {
			fato.setTitulo(titulo);
			fato.setDescricao(descricao);
			fato.setData(data);

			if (investigacaoBean.isComesHomeInvestigacao()) {
				controller.cadastrar(fato);
				InvestigacaoController ic = new InvestigacaoController();
				Investigacao i = ic.buscar(investigacaoBean.getInvestigacao().getId());
				i.getFatos().add(fato);
				ic.atualizar(i);
				investigacaoBean.setComesHomeInvestigacao(false);
				proxView = "/investigacao/home?faces-redirect=true&includeViewParams=true";
			} else {
				proxView = "/usuario/home?faces-redirect=true&includeViewParams=true";
				controller.cadastrar(fato);
			}
		}
		this.addSuccessMessage("Sucesso!");
		fato = new Fato();
		return proxView;
	}

	public String editar(Fato fato) {
		this.titulo = fato.getTitulo();
		this.descricao = fato.getDescricao();
		this.data = fato.getData();
		this.id = fato.getId();
		this.editando = true;
		return "cadastro?faces-redirect=true&includeViewParams=true";
	}

	public String excluir(Fato fato) {
		String proxima_pagina = null;
		FatoController controller = new FatoController();
		controller.excluir(fato);
		this.addSuccessMessage("Fato excluído com sucesso");
		proxima_pagina = "fatos?faces-redirect=true";
		return proxima_pagina;
	}

	public void listarFatos() {
		FatoController controller = new FatoController();
		this.fatos = controller.listar();
	}

	public List<Fato> getFatos() {
		return fatos;
	}

	public void setFatos(List<Fato> fatos) {
		this.fatos = fatos;
	}

	public String goCadastro() {
		return "/fato/cadastro?faces-redirect=true";
	}

	public String goFatos() {
		return "/fato/fatos?faces-redirect=true";
	}

	public Fato getFato() {
		return fato;
	}

	public void setFato(Fato fato) {
		this.fato = fato;
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

	public String pesquisarFatos() {
		FatoController controller = new FatoController();
		this.fatos = controller.pesquisar(argumento);

		if (fatos.isEmpty())
			this.addErrorMessage("Não há fatos para o argumento informado.");
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
