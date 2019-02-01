package br.edu.ifpb.argos.facade;

import java.util.List;
import javax.persistence.PersistenceException;
import br.edu.ifpb.argos.dao.LocalDAO;
import br.edu.ifpb.argos.dao.PersistenceUtil;
import br.edu.ifpb.argos.entity.Local;

public class LocalController {

	public void cadastrar(Local local) {
		LocalDAO dao = new LocalDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.insert(local);
		dao.commit();
	}

	public List<Local> consultar(Local Local) {
		LocalDAO dao = new LocalDAO(PersistenceUtil.getCurrentEntityManager());
		List<Local> individuos = dao.findAll();
		return individuos;
	}

	public boolean excluir(Local local) {
		boolean excluiu = false;
		LocalDAO dao = new LocalDAO();
		try {
			dao.beginTransaction();
			Local p = dao.find(local.getId());
			dao.delete(p);
			excluiu = true;

		} catch (PersistenceException e) {
			dao.rollback();
		}
		return excluiu;
	}

	public List<Local> listar() {
		LocalDAO dao = new LocalDAO();
		List<Local> Locals = dao.findAll();
		return Locals;
	}

	public void atualizar(Local local) {
		LocalDAO dao = new LocalDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.update(local);
		dao.commit();
	}

	public Local buscar(int id) {
		LocalDAO dao = new LocalDAO(PersistenceUtil.getCurrentEntityManager());
		Local l = dao.find(id);
		return l;
	}
}