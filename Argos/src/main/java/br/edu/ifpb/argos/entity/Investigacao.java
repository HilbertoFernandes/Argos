package br.edu.ifpb.argos.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name = "TB_INVESTIGACAO")
@SecondaryTable(name = "relacionamentos", pkJoinColumns = { @PrimaryKeyJoinColumn(name = "id") })

public class Investigacao {

	@Id
	@Column(name = "ID_Investigacao")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "DESCRICAO")
	private String descricao;

	@ManyToMany
	@JoinTable(name = "investigacao_pessoas", joinColumns = {
			@JoinColumn(name = "ID_Investigacao") }, inverseJoinColumns = { @JoinColumn(name = "ID_Pessoa") })
	private List<Pessoa> pessoas;

	@ManyToMany
	@JoinTable(name = "investigacao_crimes", joinColumns = {
			@JoinColumn(name = "ID_Investigacao") }, inverseJoinColumns = { @JoinColumn(name = "ID_Crime") })
	private List<Crime> crimes;

	@ManyToMany
	@JoinTable(name = "investigacao_fatos", joinColumns = {
			@JoinColumn(name = "ID_Investigacao") }, inverseJoinColumns = { @JoinColumn(name = "ID_Fato") })
	private List<Fato> fatos;

	@ManyToMany
	@JoinTable(name = "investigacao_locais", joinColumns = {
			@JoinColumn(name = "ID_Investigacao") }, inverseJoinColumns = { @JoinColumn(name = "ID_Local") })
	private List<Local> locais;

	@ManyToMany
	@JoinTable(name = "investigacao_informacoes", joinColumns = {
			@JoinColumn(name = "ID_Investigacao") }, inverseJoinColumns = { @JoinColumn(name = "ID_Informacao") })
	private List<Informacao> informacoes;

	@ManyToMany
	@JoinTable(name = "investigacao_objetos", joinColumns = {
			@JoinColumn(name = "ID_Investigacao") }, inverseJoinColumns = { @JoinColumn(name = "ID_Objeto") })
	private List<Objeto> objetos;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

	public List<Crime> getCrimes() {
		return crimes;
	}

	public void setCrimes(List<Crime> crimes) {
		this.crimes = crimes;
	}

	public List<Fato> getFatos() {
		return fatos;
	}

	public void setFatos(List<Fato> fatos) {
		this.fatos = fatos;
	}

	public List<Local> getLocais() {
		return locais;
	}

	public void setLocais(List<Local> locais) {
		this.locais = locais;
	}

	public List<Informacao> getInformacoes() {
		return informacoes;
	}

	public void setInformacoes(List<Informacao> informacoes) {
		this.informacoes = informacoes;
	}

	public List<Objeto> getObjetos() {
		return objetos;
	}

	public void setObjetos(List<Objeto> objetos) {
		this.objetos = objetos;
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

}