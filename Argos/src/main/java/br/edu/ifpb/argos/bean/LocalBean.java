package br.edu.ifpb.argos.bean;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.primefaces.model.UploadedFile;

import br.edu.ifpb.argos.entity.Investigacao;
import br.edu.ifpb.argos.entity.Local;
import br.edu.ifpb.argos.facade.InvestigacaoController;
import br.edu.ifpb.argos.facade.LocalController;

@ManagedBean(name = "localBean")
@ViewScoped
public class LocalBean extends GenericBean {
	private Integer id = null;
	private String titulo;
	private String endereco;
	private String historico;
	private Local local;
	private UploadedFile foto;
	private List<Local> locais;
	private boolean editando = false;
	@ManagedProperty("#{investigacaoBean}")
	private InvestigacaoBean investigacaoBean;

	public String salvar() throws IOException {
		String proxView = null;
		LocalController controller = new LocalController();
		local = new Local();
		if (id != null) {
			editando = true;
			if (titulo.isEmpty()) {
				titulo = "Desconhecido";
			}
			if (endereco.isEmpty()) {
				endereco = "Desconhecido";
			}
			local = controller.buscar(id);
			local.setTitulo(titulo);
			local.setEndereco(endereco);
			local.setHistorico(historico);
			controller.atualizar(local);
			proxView = "lista?faces-redirect=true";
		} else {

			if (titulo.isEmpty()) {
				titulo = "Desconhecido";
			}

			if (endereco.isEmpty()) {
				endereco = "Desconhecido";
			}

			local.setTitulo(titulo);
			local.setEndereco(endereco);
			local.setHistorico(historico);
			String local_foto = Paths
					.get(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").toString() + "/fotos")
					.toString();

			if (foto.getSize() != 0) {
				String nomeDaImagem = String.valueOf(System.currentTimeMillis());
				BufferedImage img = null;
				local.setFoto("/fotos/" + nomeDaImagem);
				img = ImageIO.read(new ByteArrayInputStream(foto.getContents()));
				ImageIO.write(img, "JPG", new File(local_foto, nomeDaImagem));
			} else {
				local.setFoto("/fotos/desconhecido.jpg");
			}

			if (investigacaoBean.isComesHomeInvestigacao()) {
				controller.cadastrar(local);
				InvestigacaoController ic = new InvestigacaoController();
				Investigacao i = ic.buscar(investigacaoBean.getInvestigacao().getId());
				i.getLocais().add(local);
				ic.atualizar(i);
				investigacaoBean.setComesHomeInvestigacao(false);
				proxView = "/investigacao/home?faces-redirect=true&includeViewParams=true";
			} else {
				proxView = "/usuario/home?faces-redirect=true&includeViewParams=true";
				controller.cadastrar(local);
			}
		}
		this.addSuccessMessage("Sucesso!");
		local = new Local();
		return proxView;
	}

	public String editar(Local local) {
		this.historico = local.getHistorico();
		this.endereco = local.getEndereco();
		this.titulo = local.getTitulo();
		this.id = local.getId();
		this.editando = true;
		return "cadastro?faces-redirect=true&includeViewParams=true";
	}

	public String excluir(Local local) {
		String proxima_pagina = null;
		LocalController controller = new LocalController();
		controller.excluir(local);
		this.addSuccessMessage("Local excluído com sucesso");
		proxima_pagina = "lista?faces-redirect=true";
		return proxima_pagina;
	}

	public void listarLocais() {
		LocalController controller = new LocalController();
		this.locais = controller.listar();
	}

	public String goHome() {
		return "/usuario/home?faces-redirect=true";
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public UploadedFile getFoto() {
		return foto;
	}

	public void setFoto(UploadedFile foto) {
		this.foto = foto;
	}

	public List<Local> getLocais() {
		return locais;
	}

	public void setLocais(List<Local> locais) {
		this.locais = locais;
	}

	public InvestigacaoBean getInvestigacaoBean() {
		return investigacaoBean;
	}

	public void setInvestigacaoBean(InvestigacaoBean investigacaoBean) {
		this.investigacaoBean = investigacaoBean;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}
}