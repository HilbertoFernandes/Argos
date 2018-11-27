package br.edu.ifpb.argos.facade;

import java.util.List;

import javax.persistence.PersistenceException;

import br.edu.ifpb.argos.dao.PersistenceUtil;
import br.edu.ifpb.argos.dao.PessoaDAO;
import br.edu.ifpb.argos.entity.Pessoa;

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
}