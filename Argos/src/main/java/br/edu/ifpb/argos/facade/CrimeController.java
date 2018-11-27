package br.edu.ifpb.argos.facade;

import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import br.edu.ifpb.argos.dao.PersistenceUtil;
import br.edu.ifpb.argos.dao.CrimeDAO;
import br.edu.ifpb.argos.entity.Crime;

public class CrimeController {

	public void cadastrar(Crime Crime) {
		CrimeDAO dao = new CrimeDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.insert(Crime);
		dao.commit();
	}

	public List<Crime> consultar(Crime Crime) {
		CrimeDAO dao = new CrimeDAO(PersistenceUtil.getCurrentEntityManager());
		List<Crime> contatos = dao.findAll();
		return contatos;
	}

	public boolean excluir(Crime crime) {
		boolean excluiu = false;
		CrimeDAO dao = new CrimeDAO();
		try {
			dao.beginTransaction();
			Crime c = dao.find(crime.getId());
			dao.delete(c);
			dao.commit();
			excluiu = true;
		} catch (PersistenceException e) {
			dao.rollback();
		}
		return excluiu;
	}

	public List<Crime> listar() {
		CrimeDAO dao = new CrimeDAO();
		List<Crime> Crimes = dao.findAll();
		return Crimes;
	}

	public Crime buscar(int Crime_id) {
		CrimeDAO dao = new CrimeDAO(PersistenceUtil.getCurrentEntityManager());
		Crime Crime_encontrado = dao.find(Crime_id);
		return Crime_encontrado;
	}

	public void atualizar(Crime Crime) {
		CrimeDAO dao = new CrimeDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.update(Crime);
		dao.commit();
	}


	public List<Crime> pesquisar(String argumento) {
		Query q = PersistenceUtil.getEntityManager().createQuery(
				"select u from Crime u where upper(u.titulo) LIKE :argumento OR u.descricao LIKE :argumento");
		argumento = argumento.toUpperCase();
		q.setParameter("argumento", "%" + argumento + "%");
		@SuppressWarnings("unchecked")
		List<Crime> Crimes = (List<Crime>) q.getResultList();
		return Crimes;
	}
}
