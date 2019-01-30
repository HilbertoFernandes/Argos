package br.edu.ifpb.argos.dao;

import javax.persistence.EntityManager;
import br.edu.ifpb.argos.entity.Local;

public class LocalDAO extends GenericDAO<Local, Integer> {

	public LocalDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public LocalDAO(EntityManager em) {
		super(em);
	}
}