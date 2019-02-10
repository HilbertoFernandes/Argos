package br.edu.ifpb.argos.bean;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.model.UploadedFile;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import br.edu.ifpb.argos.entity.Investigacao;
import br.edu.ifpb.argos.entity.Pessoa;
import br.edu.ifpb.argos.facade.InvestigacaoController;
import br.edu.ifpb.argos.facade.PessoaController;

@ManagedBean(name = "pessoaBean")
@ViewScoped
public class PessoaBean extends GenericBean {
	private Integer id = null;
	private Integer investigacao;
	private String nome;
	private String apelido;
	private String historico;
	private Pessoa pessoa;
	private String argumento;
	private boolean editando = false;
	private UploadedFile foto;
	private List<Pessoa> pessoas;
	@ManagedProperty("#{investigacaoBean}")
	private InvestigacaoBean investigacaoBean;

	public String salvar() throws IOException {
		String proxView = null;
		PessoaController controller = new PessoaController();
		pessoa = new Pessoa();
		if (id != null) {
			pessoa = controller.buscar(id);
			
			if (nome.isEmpty()) {
				nome = "Desconhecido";
			}
		
			if (apelido.isEmpty()) {
				apelido = "Desconhecido";
			}

			pessoa.setNome(nome);
			pessoa.setApelido(apelido);
			pessoa.setHistorico(historico);
			controller.atualizar(pessoa);
			proxView = "lista?faces-redirect=true";
		} else {

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

			if (investigacaoBean.isComesHomeInvestigacao()) {
				controller.cadastrar(pessoa);
				InvestigacaoController ic = new InvestigacaoController();
				Investigacao i = ic.buscar(investigacaoBean.getInvestigacao().getId());
				i.getPessoas().add(pessoa);
				ic.atualizar(i);
				investigacaoBean.setComesHomeInvestigacao(false);
				proxView = "/investigacao/home?faces-redirect=true&includeViewParams=true";
			} else {
				controller.cadastrar(pessoa);
				proxView = "album?faces-redirect=true&includeViewParams=true";
			}
		}
		this.addSuccessMessage("Sucesso!");
		pessoa = new Pessoa();
		return proxView;
	}

	public String editar(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.apelido = pessoa.getApelido();
		this.historico = pessoa.getHistorico();
		this.editando = true;
		return "cadastro?faces-redirect=true&includeViewParams=true";
	}

	public String excluir(Pessoa pessoa) {
		String proxima_pagina = null;
		PessoaController controller = new PessoaController();
		controller.excluir(pessoa);
		this.addSuccessMessage("Pessoa excluída com sucesso");
		proxima_pagina = "lista?faces-redirect=true";
		return proxima_pagina;
	}

	public void pessoaPDF(Pessoa pessoa) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline=filename=file.pdf");
		try {
			Document document = new Document();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);
			document.open();
		
			document.add(Image.getInstance(String.format(FacesContext.getCurrentInstance().getExternalContext().getRealPath("\\") + pessoa.getFoto())));
			document.add(new Paragraph("Nome :"+pessoa.getNome()));
			document.add(new Paragraph("Apelido : "+ pessoa.getApelido()));
			document.add(new Paragraph("Histórico :"+ pessoa.getHistorico()));
			document.close();
			response.setHeader("Expires", "0");
			response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
			response.setHeader("Pragma", "public");
			response.setContentType("application/pdf");
			response.setContentLength(baos.size());
			ServletOutputStream os = response.getOutputStream();
			baos.writeTo(os);
			os.flush();
			os.close();
		} catch (DocumentException e) {
		} catch (IOException e) {
		} catch (Exception ex) {
		}
		context.responseComplete();
	}

	public void listarPessoas() {
		PessoaController controller = new PessoaController();

		this.pessoas = controller.listar();
	}

	public void pesquisar() {
		if (!argumento.trim().isEmpty()) {
			PessoaController controller = new PessoaController();
			this.pessoas = controller.pesquisar(argumento);
		} else {
			this.pessoas.clear();
		}
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

	public Integer getInvestigacao() {
		return investigacao;
	}

	public void setInvestigacao(Integer investigacao) {
		this.investigacao = investigacao;
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

	public String getArgumento() {
		return argumento;
	}

	public void setArgumento(String argumento) {
		this.argumento = argumento;
	}

}