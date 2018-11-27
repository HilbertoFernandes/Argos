package br.edu.ifpb.argos.facade;

import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import br.edu.ifpb.argos.dao.PersistenceUtil;
import br.edu.ifpb.argos.dao.ObjetoDAO;
import br.edu.ifpb.argos.entity.Objeto;

public class ObjetoController {

	public void cadastrar(Objeto Objeto) {
		ObjetoDAO dao = new ObjetoDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.insert(Objeto);
		dao.commit();
	}

	public List<Objeto> consultar(Objeto Objeto) {
		ObjetoDAO dao = new ObjetoDAO(PersistenceUtil.getCurrentEntityManager());
		List<Objeto> contatos = dao.findAll();
		return contatos;
	}

	public boolean excluir(Objeto objeto) {
		boolean excluiu = false;
		ObjetoDAO dao = new ObjetoDAO();
		try {
			dao.beginTransaction();
			Objeto c = dao.find(objeto.getId());
			dao.delete(c);
			dao.commit();
			excluiu = true;
		} catch (PersistenceException e) {
			dao.rollback();
		}
		return excluiu;
	}

	public List<Objeto> listar() {
		ObjetoDAO dao = new ObjetoDAO();
		List<Objeto> Objetos = dao.findAll();
		return Objetos;
	}

	public Objeto buscar(int Objeto_id) {
		ObjetoDAO dao = new ObjetoDAO(PersistenceUtil.getCurrentEntityManager());
		Objeto Objeto_encontrado = dao.find(Objeto_id);
		return Objeto_encontrado;
	}

	public void atualizar(Objeto Objeto) {
		ObjetoDAO dao = new ObjetoDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.update(Objeto);
		dao.commit();
	}


	public List<Objeto> pesquisar(String argumento) {
		Query q = PersistenceUtil.getEntityManager().createQuery(
				"select u from Objeto u where upper(u.titulo) LIKE :argumento OR u.descricao LIKE :argumento");
		argumento = argumento.toUpperCase();
		q.setParameter("argumento", "%" + argumento + "%");
		@SuppressWarnings("unchecked")
		List<Objeto> Objetos = (List<Objeto>) q.getResultList();
		return Objetos;
	}
}
