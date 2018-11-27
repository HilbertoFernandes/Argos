package br.edu.ifpb.argos.dao;

import javax.persistence.EntityManager;
import br.edu.ifpb.argos.entity.Crime;

public class CrimeDAO extends GenericDAO<Crime, Integer> {

	public CrimeDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public CrimeDAO(EntityManager em) {
		super(em);
	}
}
