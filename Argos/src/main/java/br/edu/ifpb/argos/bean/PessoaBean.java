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

import br.edu.ifpb.argos.entity.Pessoa;
import br.edu.ifpb.argos.facade.PessoaController;

@ManagedBean(name = "pessoaBean")
@ViewScoped
public class PessoaBean extends GenericBean {
	private Integer id = null;
	private String nome;
	private String apelido;
	private String historico;
	private Pessoa pessoa;
	private UploadedFile foto;
	private List<Pessoa> pessoas;

	public String salvar() throws IOException {
		String proxView = null;
		PessoaController controller = new PessoaController();
		pessoa = new Pessoa();

		if (nome.isEmpty()) {
			nome = "Desconhecido";
		}

		if (apelido.isEmpty()) {
			apelido = "Desconhecido";
		}

		pessoa.setNome(nome);
		pessoa.setApelido(apelido);
		pessoa.setHistorico(historico);
		String local = Paths
				.get(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").toString() + "/fotos")
				.toString();

		if (foto.getSize() != 0) {
			String nomeDaImagem = String.valueOf(System.currentTimeMillis());
			BufferedImage img = null;
			pessoa.setFoto("/fotos/" + nomeDaImagem);
			img = ImageIO.read(new ByteArrayInputStream(foto.getContents()));
			ImageIO.write(img, "JPG", new File(local, nomeDaImagem));
		} else {
			pessoa.setFoto("/fotos/desconhecido.jpg");
		}

		controller.cadastrar(pessoa);
		this.addSuccessMessage("Pessoa Cadastrada, obrigado!");
		pessoa = new Pessoa();
		proxView = "/usuario/home?faces-redirect=true";
		return proxView;
	}

	public void listarPessoas() {
		PessoaController controller = new PessoaController();
		this.pessoas = controller.listar();
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

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public UploadedFile getFoto() {
		return foto;
	}

	public void setFoto(UploadedFile foto) {
		this.foto = foto;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}