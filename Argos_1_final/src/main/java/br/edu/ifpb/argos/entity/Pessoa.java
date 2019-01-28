package br.edu.ifpb.argos.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PESSOA")
public class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_PESSOA")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "NOME")
	private String nome;
	
	@Column(name = "APELIDO")
	private String apelido;
	
	@Lob
	@Column(name = "FOTO", columnDefinition="BLOB NOT NULL")
	private Byte[] foto;
	
	@Lob
	@Column(name = "HISTORICO")
	private String historico;
	
	@OneToMany(mappedBy="pessoa")
	@Column(name = "INVESTIGACOES")
	private List<Investigacao> investigacoes;
	
	
	public Pessoa() {
	}
	
	public Pessoa(Integer id, String nome, String apelido, Byte[] foto, String historico) {
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

	public Byte[] getFoto() {
		return foto;
	}

	public void setFoto(Byte[] foto) {
		this.foto = foto;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public List<Investigacao> getInvestigacoes() {
		return investigacoes;
	}

	public void setInvestigacoes(List<Investigacao> investigacoes) {
		this.investigacoes = investigacoes;
	}
}