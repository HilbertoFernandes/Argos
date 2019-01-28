package br.edu.ifpb.argos.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "TB_Pessoa")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_Pessoa")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "NOME")
	private String nome;

	@Column(name = "APELIDO")
	private String apelido;

	@Column(name = "HISTORICO")
	private String historico;

	@Column(name = "FOTO")
	private String foto;

	public Pessoa() {
	}

	public Pessoa(Integer id, String nome, String apelido, String historico, String foto) {
		super();
		this.id = id;
		this.nome = nome;
		this.apelido = apelido;
		this.foto = foto;
		this.historico = historico;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

}
