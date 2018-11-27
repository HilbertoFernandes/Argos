package br.edu.ifpb.argos.facade;

import java.util.List;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import br.edu.ifpb.argos.dao.InformacaoDAO;
import br.edu.ifpb.argos.dao.PersistenceUtil;
import br.edu.ifpb.argos.entity.Informacao;

public class InformacaoController {

	public void cadastrar(Informacao informacao) {
		InformacaoDAO dao = new InformacaoDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.insert(informacao);
		dao.commit();
	}

	public List<Informacao> consultar(Informacao Informacao) {
		InformacaoDAO dao = new InformacaoDAO(PersistenceUtil.getCurrentEntityManager());
		List<Informacao> contatos = dao.findAll();
		return contatos;
	}

	public boolean excluir(Informacao Informacao) {
		boolean excluiu = false;
		InformacaoDAO dao = new InformacaoDAO();
		try {
			dao.beginTransaction();
			Informacao c = dao.find(Informacao.getId());
			dao.delete(c);
			dao.commit();
			excluiu = true;
		} catch (PersistenceException e) {
			dao.rollback();
		}
		return excluiu;
	}

	public List<Informacao> listar() {
		InformacaoDAO dao = new InformacaoDAO();
		List<Informacao> Informacaos = dao.findAll();
		return Informacaos;
	}

	public Informacao buscar(int Informacao_id) {
		InformacaoDAO dao = new InformacaoDAO(PersistenceUtil.getCurrentEntityManager());
		Informacao Informacao_encontrado = dao.find(Informacao_id);
		return Informacao_encontrado;
	}

	public void atualizar(Informacao informacao) {
		InformacaoDAO dao = new InformacaoDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.update(informacao);
		dao.commit();
	}


	public List<Informacao> pesquisar(String argumento) {
		Query q = PersistenceUtil.getEntityManager().createQuery(
				"select u from Informacao u where upper(u.titulo) LIKE :argumento OR u.descricao LIKE :argumento");
		argumento = argumento.toUpperCase();
		q.setParameter("argumento", "%" + argumento + "%");
		@SuppressWarnings("unchecked")
		List<Informacao> Informacaos = (List<Informacao>) q.getResultList();
		return Informacaos;
	}
}
