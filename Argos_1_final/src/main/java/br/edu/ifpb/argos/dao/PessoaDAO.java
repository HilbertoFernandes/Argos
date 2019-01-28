package br.edu.ifpb.argos.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.edu.ifpb.argos.entity.Pessoa;

public class PessoaDAO extends GenericDAO<Pessoa, Integer> {
	
	public PessoaDAO() {
		this(PersistenceUtil.getCurrentEntityManager());
	}

	public PessoaDAO(EntityManager em) {
		super(em);
	}
	
	public Pessoa findByNome(String nome) {
		Query q = this.getEntityManager().createQuery("Select p from Pessoa p where p.nome = :nome");
		q.setParameter("nome", nome);
		Pessoa p = null;
		try {
			p = (Pessoa) q.getSingleResult();
			
		} catch (NoResultException e) {
		}
		return p;
	}
	
	public Pessoa findByApelido(String apelido) {
		Query q = this.getEntityManager().createQuery("Select p from Pessoa p where p.apelido = :apelido");
		q.setParameter("apelido", apelido);
		Pessoa p = null;
		try {
			p = (Pessoa) q.getSingleResult();
			
		} catch (NoResultException e) {
		}
		return p;
	}
}