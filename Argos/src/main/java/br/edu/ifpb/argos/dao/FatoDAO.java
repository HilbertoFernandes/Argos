package br.edu.ifpb.argos.dao;

import javax.persistence.EntityManager;
import br.edu.ifpb.argos.entity.Fato;

public class FatoDAO extends GenericDAO<Fato, Integer> {

	public FatoDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public FatoDAO(EntityManager em) {
		super(em);
	}
}
