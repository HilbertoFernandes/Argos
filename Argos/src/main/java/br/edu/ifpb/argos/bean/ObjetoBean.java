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
import br.edu.ifpb.argos.entity.Objeto;
import br.edu.ifpb.argos.facade.InvestigacaoController;
import br.edu.ifpb.argos.facade.ObjetoController;

@ManagedBean(name = "objetoBean")
@ViewScoped
public class ObjetoBean extends GenericBean {
	private Integer id = null;
	private String nome;
	private String descricao;
	private boolean editando = false;
	private Objeto objeto;
	private UploadedFile foto;
	private List<Objeto> objetos;
	@ManagedProperty("#{investigacaoBean}")
	private InvestigacaoBean investigacaoBean;

	public String salvar() throws IOException {
		String proxView = null;
		ObjetoController controller = new ObjetoController();
		objeto = new Objeto();
		if (id != null) {
			editando = true;
			if (nome.isEmpty()) {
				nome = "Desconhecido";
			}
			objeto = controller.buscar(id);
			objeto.setNome(nome);
			objeto.setDescricao(descricao);
			controller.atualizar(objeto);
			proxView = "lista?faces-redirect=true";
		} else {
			if (nome.isEmpty()) {
				nome = "Desconhecido";
			}
			objeto.setNome(nome);
			objeto.setDescricao(descricao);
			String local_foto = Paths
					.get(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").toString() + "/fotos")
					.toString();

			if (foto.getSize() != 0) {
				String nomeDaImagem = String.valueOf(System.currentTimeMillis());
				BufferedImage img = null;
				objeto.setFoto("/fotos/" + nomeDaImagem);
				img = ImageIO.read(new ByteArrayInputStream(foto.getContents()));
				ImageIO.write(img, "JPG", new File(local_foto, nomeDaImagem));
			} else {
				objeto.setFoto("/fotos/desconhecido.jpg");
			}

			if (investigacaoBean.isComesHomeInvestigacao()) {
				controller.cadastrar(objeto);
				InvestigacaoController ic = new InvestigacaoController();
				Investigacao i = ic.buscar(investigacaoBean.getInvestigacao().getId());
				i.getObjetos().add(objeto);
				ic.atualizar(i);
				investigacaoBean.setComesHomeInvestigacao(false);
				proxView = "/investigacao/home?faces-redirect=true&includeViewParams=true";
			} else {
				proxView = "/usuario/home?faces-redirect=true&includeViewParams=true";
				controller.cadastrar(objeto);
			}
		}
		this.addSuccessMessage("Sucesso!");
		objeto = new Objeto();
		return proxView;
	}

	public void listarObjetos() {
		ObjetoController controller = new ObjetoController();
		this.objetos = controller.listar();
	}

	public String editar(Objeto objeto) {
		this.nome = objeto.getNome();
		this.descricao = objeto.getDescricao();
		this.editando = true;
		return "cadastro?faces-redirect=true&includeViewParams=true";
	}

	public String excluir(Objeto objeto) {
		String proxima_pagina = null;
		ObjetoController controller = new ObjetoController();
		controller.excluir(objeto);
		this.addSuccessMessage("Objeto excluído com sucesso");
		proxima_pagina = "lista?faces-redirect=true";
		return proxima_pagina;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public UploadedFile getFoto() {
		return foto;
	}

	public void setFoto(UploadedFile foto) {
		this.foto = foto;
	}

	public List<Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(List<Objeto> objetos) {
		this.objetos = objetos;
	}

	public InvestigacaoBean getInvestigacaoBean() {
		return investigacaoBean;
	}

	public void setInvestigacaoBean(InvestigacaoBean investigacaoBean) {
		this.investigacaoBean = investigacaoBean;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

}