package br.edu.ifpb.argos.dao;

import javax.persistence.EntityManager;

import br.edu.ifpb.argos.entity.Investigacao;

public class InvestigacaoDAO extends GenericDAO<Investigacao, Integer> {
	
	public InvestigacaoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public InvestigacaoDAO(EntityManager em) {
		super(em);
	}
}