package br.edu.ifpb.argos.facade;

import java.util.List;
import javax.persistence.PersistenceException;
import br.edu.ifpb.argos.dao.InvestigacaoDAO;
import br.edu.ifpb.argos.dao.PersistenceUtil;
import br.edu.ifpb.argos.entity.Investigacao;

public class InvestigacaoController {

	public void cadastrar(Investigacao Investigacao) {
		InvestigacaoDAO dao = new InvestigacaoDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.insert(Investigacao);
		dao.commit();
	}

	public Investigacao buscar(int id) {
		InvestigacaoDAO dao = new InvestigacaoDAO(PersistenceUtil.getCurrentEntityManager());
		Investigacao investigacao = dao.find(id);
		return investigacao;
	}

	public void atualizar(Investigacao investigacao) {
		InvestigacaoDAO dao = new InvestigacaoDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.update(investigacao);
		dao.commit();
	}

	public List<Investigacao> listar() {
		InvestigacaoDAO dao = new InvestigacaoDAO();
		List<Investigacao> investigacoes = dao.findAll();
		return investigacoes;
	}

	public boolean excluir(Investigacao investigacao) {
		boolean excluiu = false;
		InvestigacaoDAO dao = new InvestigacaoDAO();
		try {
			dao.beginTransaction();
			Investigacao i = dao.find(investigacao.getId());
			dao.delete(i);
			dao.commit();
			excluiu = true;
		} catch (PersistenceException e) {
			dao.rollback();
		}
		return excluiu;
	}
}