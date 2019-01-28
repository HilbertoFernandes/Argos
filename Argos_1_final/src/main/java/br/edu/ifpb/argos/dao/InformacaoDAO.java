package br.edu.ifpb.argos.dao;

import javax.persistence.EntityManager;
import br.edu.ifpb.argos.entity.Informacao;

public class InformacaoDAO extends GenericDAO<Informacao, Integer> {

	public InformacaoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public InformacaoDAO(EntityManager em) {
		super(em);
	}
}
