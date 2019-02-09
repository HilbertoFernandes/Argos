package br.edu.ifpb.argos.bean;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.edu.ifpb.argos.entity.Crime;
import br.edu.ifpb.argos.entity.Fato;
import br.edu.ifpb.argos.entity.Informacao;
import br.edu.ifpb.argos.entity.Investigacao;
import br.edu.ifpb.argos.entity.Local;
import br.edu.ifpb.argos.entity.Objeto;
import br.edu.ifpb.argos.entity.Pessoa;
import br.edu.ifpb.argos.facade.InvestigacaoController;

@ManagedBean(name = "investigacaoBean")
@SessionScoped
public class InvestigacaoBean extends GenericBean {
	private Integer id = null;
	private boolean comesHomeInvestigacao;
	private String titulo;
	private String descricao;
	private List<Crime> crimes;
	private List<Local> locais;
	private List<Fato> fatos;
	private List<Objeto> objetos;
	private List<Pessoa> pessoas;
	private List<Informacao> informacoes;
	private Investigacao investigacao;
	private List<Investigacao> investigacoes;
	private boolean editando = false;

	public String salvar() {
		String proxView = null;
		InvestigacaoController controller = new InvestigacaoController();
		investigacao = new Investigacao();
		if (id != null) {
			editando = true;
			investigacao = controller.buscar(id);
			investigacao.setTitulo(titulo);
			investigacao.setDescricao(descricao);
			controller.atualizar(investigacao);
			proxView = "lista?faces-redirect=true";
		} else {
			investigacao = new Investigacao();
			investigacao.setTitulo(titulo);
			investigacao.setDescricao(descricao);
			controller.cadastrar(investigacao);
			this.addSuccessMessage("investigacao salva com sucesso");
			proxView = "lista?faces-redirect=true";
		}
		return proxView;
	}

	public String excluir(Investigacao investigacao) {
		String proxView = null;
		InvestigacaoController controller = new InvestigacaoController();
		controller.excluir(investigacao);
		this.addSuccessMessage("Investigação excluída com sucesso");
		proxView = "/lista/home?faces-redirect=true";
		return proxView;
	}

	public String editar(Investigacao investigacao) {
		this.titulo = investigacao.getTitulo();
		this.descricao = investigacao.getDescricao();
		this.id = investigacao.getId();
		System.out.println(investigacao.getId());
		System.out.println(investigacao.getTitulo());
		return "cadastro?faces-redirect=true&includeViewParams=true";
	}

	public void listarInvestigacoes() {
		InvestigacaoController controller = new InvestigacaoController();
		this.investigacoes = controller.listar();
	}

	public String goHome() {
		return "/usuario/home?faces-redirect=true";
	}

	public String goAdicionarPessoa() {
		return "/investigacao/cadastro_pessoa?faces-redirect=true";
	}

	public String goAdicionarCrime() {
		return "/investigacao/cadastro_crime?faces-redirect=true";
	}

	public String goAdicionarFato() {
		return "/investigacao/cadastro_fato?faces-redirect=true";
	}

	public String goAdicionarObjeto() {
		return "/investigacao/cadastro_objeto?faces-redirect=true";
	}

	public String goAdicionarLocal() {
		return "/investigacao/cadastro_local?faces-redirect=true";
	}

	public String goAdicionarInformacao() {
		return "/investigacao/cadastro_informacao?faces-redirect=true";
	}

	public String goAssociarPessoa() {
		return "/investigacao/lista_pessoa?faces-redirect=true";
	}

	public String goAssociarCrime() {
		return "/investigacao/lista_pessoa?faces-redirect=true";
	}

	public String goAssociarFato() {
		return "/investigacao/lista_fato?faces-redirect=true";
	}

	public String goAssociarObjeto() {
		return "/investigacao/lista_objeto?faces-redirect=true";
	}

	public String goAssociarLocal() {
		return "/investigacao/lista_local?faces-redirect=true";
	}

	public String goAssociarInformacao() {
		return "/investigacao/lista_informacao?faces-redirect=true";
	}

	public String goHomeInvestigacao(int investigacao) {
		InvestigacaoController controller = new InvestigacaoController();
		this.comesHomeInvestigacao = true;
		this.investigacao = controller.buscar(investigacao);
		this.fatos = this.investigacao.getFatos();
		this.objetos = this.investigacao.getObjetos();
		this.pessoas = this.investigacao.getPessoas();
		this.locais = this.investigacao.getLocais();
		this.informacoes = this.investigacao.getInformacoes();
		this.crimes = this.investigacao.getCrimes();
		return "/investigacao/home?faces-redirect=true&includeViewParams=true";
	}

	public String associarPessoa(Pessoa pessoa) {
		InvestigacaoController controller = new InvestigacaoController();
		this.investigacao = controller.buscar(this.investigacao.getId());
		this.investigacao.getPessoas().add(pessoa);
		controller.atualizar(investigacao);
		return this.goHomeInvestigacao(this.investigacao.getId());
	}

	public String associarCrime(Crime crime) {
		InvestigacaoController controller = new InvestigacaoController();
		this.investigacao = controller.buscar(this.investigacao.getId());
		this.investigacao.getCrimes().add(crime);
		controller.atualizar(investigacao);
		return this.goHomeInvestigacao(this.investigacao.getId());
	}

	public String associarObjeto(Objeto objeto) {
		InvestigacaoController controller = new InvestigacaoController();
		this.investigacao = controller.buscar(this.investigacao.getId());
		this.investigacao.getObjetos().add(objeto);
		controller.atualizar(investigacao);
		return this.goHomeInvestigacao(this.investigacao.getId());
	}

	public String associarLocal(Local local) {
		InvestigacaoController controller = new InvestigacaoController();
		this.investigacao = controller.buscar(this.investigacao.getId());
		this.investigacao.getLocais().add(local);
		controller.atualizar(investigacao);
		return this.goHomeInvestigacao(this.investigacao.getId());
	}

	public String associarFato(Fato fato) {
		InvestigacaoController controller = new InvestigacaoController();
		this.investigacao = controller.buscar(this.investigacao.getId());
		this.investigacao.getFatos().add(fato);
		controller.atualizar(investigacao);
		return this.goHomeInvestigacao(this.investigacao.getId());
	}

	public String associarInformacao(Informacao informacao) {
		InvestigacaoController controller = new InvestigacaoController();
		this.investigacao = controller.buscar(this.investigacao.getId());
		this.investigacao.getInformacoes().add(informacao);
		controller.atualizar(investigacao);
		return this.goHomeInvestigacao(this.investigacao.getId());
	}

	public String excluirInformacao(Informacao informacao) {
		InvestigacaoController controller = new InvestigacaoController();
		this.investigacao = controller.buscar(this.investigacao.getId());
		this.investigacao.getInformacoes().remove(informacao);
		controller.atualizar(investigacao);
		return this.goHomeInvestigacao(this.investigacao.getId());
	}

	public String excluirObjeto(Objeto objeto) {
		InvestigacaoController controller = new InvestigacaoController();
		this.investigacao = controller.buscar(this.investigacao.getId());
		this.investigacao.getObjetos().remove(objeto);
		controller.atualizar(investigacao);
		return this.goHomeInvestigacao(this.investigacao.getId());
	}

	public String excluirCrime(Crime crime) {
		InvestigacaoController controller = new InvestigacaoController();
		this.investigacao = controller.buscar(this.investigacao.getId());
		this.investigacao.getCrimes().remove(crime);
		controller.atualizar(investigacao);
		return this.goHomeInvestigacao(this.investigacao.getId());
	}

	public String excluirFato(Fato fato) {
		InvestigacaoController controller = new InvestigacaoController();
		this.investigacao = controller.buscar(this.investigacao.getId());
		this.investigacao.getFatos().remove(fato);
		controller.atualizar(investigacao);
		return this.goHomeInvestigacao(this.investigacao.getId());
	}

	public String excluirPessoa(Pessoa pessoa) {
		InvestigacaoController controller = new InvestigacaoController();
		this.investigacao = controller.buscar(this.investigacao.getId());
		this.investigacao.getPessoas().remove(pessoa);
		controller.atualizar(investigacao);
		return this.goHomeInvestigacao(this.investigacao.getId());
	}

	public String excluirLocal(Local local) {
		InvestigacaoController controller = new InvestigacaoController();
		this.investigacao = controller.buscar(this.investigacao.getId());
		this.investigacao.getLocais().remove(local);
		controller.atualizar(investigacao);
		return this.goHomeInvestigacao(this.investigacao.getId());
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

	public List<Crime> getCrimes() {
		return crimes;
	}

	public void setCrimes(List<Crime> crimes) {
		this.crimes = crimes;
	}

	public List<Local> getLocais() {
		return locais;
	}

	public void setLocais(List<Local> locais) {
		this.locais = locais;
	}

	public List<Fato> getFatos() {
		return fatos;
	}

	public void setFatos(List<Fato> fatos) {
		this.fatos = fatos;
	}

	public List<Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(List<Objeto> objetos) {
		this.objetos = objetos;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Informacao> getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(List<Informacao> informacoes) {
		this.informacoes = informacoes;
	}

	public Investigacao getInvestigacao() {
		return investigacao;
	}

	public void setInvestigacao(Investigacao investigacao) {
		this.investigacao = investigacao;
	}

	public List<Investigacao> getInvestigacoes() {
		return investigacoes;
	}

	public void setInvestigacoes(List<Investigacao> investigacoes) {
		this.investigacoes = investigacoes;
	}

	public boolean isComesHomeInvestigacao() {
		return comesHomeInvestigacao;
	}

	public void setComesHomeInvestigacao(boolean comesHomeInvestigacao) {
		this.comesHomeInvestigacao = comesHomeInvestigacao;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

}