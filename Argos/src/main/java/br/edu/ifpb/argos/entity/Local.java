package br.edu.ifpb.argos.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Local")
public class Local implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_LOCAL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToMany(mappedBy = "locais")
	private List<Investigacao> investigacoes;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "ENDERECO")
	private String endereco;

	@Column(name = "DESCRICAO")
	private String descricao;

	@Column(name = "FOTO")
	private String foto;

	public Local() {
	}

	public Local(Integer id, String titulo, String endereco, String descricao, String foto) {
		super();
		this.titulo = titulo;
		this.id = id;
		this.endereco = endereco;
		this.descricao = descricao;
		this.foto = foto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getData() {
		return foto;
	}

	public void setData(String foto) {
		this.foto = foto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Investigacao> getInvestigacoes() {
		return investigacoes;
	}

	public void setInvestigacoes(List<Investigacao> investigacoes) {
		this.investigacoes = investigacoes;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}