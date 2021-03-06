package br.edu.ifpb.argos.facade;

import java.util.List;

import javax.persistence.PersistenceException;
import br.edu.ifpb.argos.dao.PersistenceUtil;
import br.edu.ifpb.argos.dao.PessoaDAO;
import br.edu.ifpb.argos.entity.Pessoa;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

public class PessoaController {

	public void cadastrar(Pessoa pessoa) {
		PessoaDAO dao = new PessoaDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.insert(pessoa);
		dao.commit();
	}

	public List<Pessoa> consultar(Pessoa pessoa) {
		PessoaDAO dao = new PessoaDAO(PersistenceUtil.getCurrentEntityManager());
		List<Pessoa> individuos = dao.findAll();
		return individuos;
	}

	public boolean excluir(Pessoa pessoa) {
		boolean excluiu = false;
		PessoaDAO dao = new PessoaDAO();
		try {
			dao.beginTransaction();
			Pessoa p = dao.find(pessoa.getId());
			dao.delete(p);
			dao.commit();
			excluiu = true;

		} catch (PersistenceException e) {
			dao.rollback();
		}
		return excluiu;
	}

	public List<Pessoa> listar() {
		PessoaDAO dao = new PessoaDAO();
		List<Pessoa> pessoas = dao.findAll();
		return pessoas;
	}

	public Pessoa buscar(String nome) {
		PessoaDAO dao = new PessoaDAO();
		Pessoa pessoaEncontrada = dao.findByNome(nome);
		return pessoaEncontrada;
	}

	public void atualizar(Pessoa pessoa) {
		PessoaDAO dao = new PessoaDAO(PersistenceUtil.getCurrentEntityManager());
		dao.beginTransaction();
		dao.update(pessoa);
		dao.commit();
	}

	public Pessoa buscar(int id) {
		PessoaDAO dao = new PessoaDAO(PersistenceUtil.getCurrentEntityManager());
		Pessoa p = dao.find(id);
		return p;
	}
	
	public List<Pessoa> pesquisar(String argumento) {
		Query q = PersistenceUtil.getEntityManager().createQuery(
				"select p from Pessoa p where upper(p.nome) LIKE :argumento OR upper(p.apelido) LIKE :argumento OR upper(p.historico) LIKE :argumento");
		argumento = argumento.toUpperCase();
		q.setParameter("argumento", "%" + argumento + "%");
		@SuppressWarnings("unchecked")
		List<Pessoa> pessoas = (List<Pessoa>) q.getResultList();
		return pessoas;
	}
}