package br.edu.ifpb.argos.bean;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;

import org.primefaces.model.UploadedFile;

import br.edu.ifpb.argos.entity.Objeto;
import br.edu.ifpb.argos.facade.ObjetoController;

@ManagedBean(name = "objetoBean")
@ViewScoped
public class ObjetoBean extends GenericBean {
	private Integer id = null;
	private String nome;
	private String descricao;
	private UploadedFile foto;
	private List<Objeto> objetos;

	public String salvar() throws IOException {
		String proxView = null;
		ObjetoController controller = new ObjetoController();
		Objeto objeto = new Objeto();

		objeto.setNome(nome);
		objeto.setDescricao(descricao);
		String objeto_foto = Paths
				.get(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").toString() + "/fotos")
				.toString();

		if (foto.getSize() != 0) {
			String nomeDaImagem = String.valueOf(System.currentTimeMillis());
			BufferedImage img = null;
			objeto.setFoto("/fotos/" + nomeDaImagem);
			img = ImageIO.read(new ByteArrayInputStream(foto.getContents()));
			ImageIO.write(img, "JPG", new File(objeto_foto, nomeDaImagem));
		} else {
			objeto.setFoto("/fotos/desconhecido.jpg");
		}

		controller.cadastrar(objeto);
		this.addSuccessMessage("Pessoa Cadastrada, obrigado!");
		objeto = new Objeto();
		proxView = "/usuario/home?faces-redirect=true";
		return proxView;
	}

	public void listarObjetos() {
		ObjetoController controller = new ObjetoController();
		this.objetos = controller.listar();
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

}