package br.edu.ifpb.argos.facade;

import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import br.edu.ifpb.argos.dao.PersistenceUtil;
import br.edu.ifpb.argos.dao.FatoDAO;
import br.edu.ifpb.argos.entity.Fato;

public class FatoController {

	public void cadastrar(Fato Fato) {
		FatoDAO dao = new FatoDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.insert(Fato);
		dao.commit();
	}

	public List<Fato> consultar(Fato Fato) {
		FatoDAO dao = new FatoDAO(PersistenceUtil.getCurrentEntityManager());
		List<Fato> contatos = dao.findAll();
		return contatos;
	}

	public boolean excluir(Fato fato) {
		boolean excluiu = false;
		FatoDAO dao = new FatoDAO();
		try {
			dao.beginTransaction();
			Fato f = dao.find(fato.getId());
			dao.delete(f);
			dao.commit();
			excluiu = true;
		} catch (PersistenceException e) {
			dao.rollback();
		}
		return excluiu;
	}

	public List<Fato> listar() {
		FatoDAO dao = new FatoDAO();
		List<Fato> Fatos = dao.findAll();
		return Fatos;
	}

	public Fato buscar(int Fato_id) {
		FatoDAO dao = new FatoDAO(PersistenceUtil.getCurrentEntityManager());
		Fato Fato_encontrado = dao.find(Fato_id);
		return Fato_encontrado;
	}

	public void atualizar(Fato Fato) {
		FatoDAO dao = new FatoDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.update(Fato);
		dao.commit();
	}


	public List<Fato> pesquisar(String argumento) {
		Query q = PersistenceUtil.getEntityManager().createQuery(
				"select u from Fato u where upper(u.titulo) LIKE :argumento OR u.descricao LIKE :argumento");
		argumento = argumento.toUpperCase();
		q.setParameter("argumento", "%" + argumento + "%");
		@SuppressWarnings("unchecked")
		List<Fato> Fatos = (List<Fato>) q.getResultList();
		return Fatos;
	}
}
