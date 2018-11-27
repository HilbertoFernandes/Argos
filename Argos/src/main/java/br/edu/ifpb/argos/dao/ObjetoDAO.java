package br.edu.ifpb.argos.dao;

import javax.persistence.EntityManager;
import br.edu.ifpb.argos.entity.Objeto;

public class ObjetoDAO extends GenericDAO<Objeto, Integer> {

	public ObjetoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public ObjetoDAO(EntityManager em) {
		super(em);
	}
}