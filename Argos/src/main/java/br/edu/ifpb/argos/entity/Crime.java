package br.edu.ifpb.argos.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_Crime")
public class Crime implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_CRIME")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToMany(mappedBy = "crimes")
	private List<Investigacao> investigacoes;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "HISTORICO")
	private String historico;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATA")
	private Date data;

	public Crime() {
	}

	public Crime(Integer id, String titulo, String descricao, Date data) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.historico = descricao;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}