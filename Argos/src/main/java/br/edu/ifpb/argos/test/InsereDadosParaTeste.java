package br.edu.ifpb.argos.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.edu.ifpb.argos.dao.CrimeDAO;
import br.edu.ifpb.argos.dao.InvestigacaoDAO;
import br.edu.ifpb.argos.dao.ManagedEMContext;
import br.edu.ifpb.argos.dao.PersistenceUtil;
import br.edu.ifpb.argos.dao.UsuarioDAO;
import br.edu.ifpb.argos.entity.Crime;
import br.edu.ifpb.argos.entity.Investigacao;
import br.edu.ifpb.argos.entity.Usuario;
import br.edu.ifpb.argos.facade.InvestigacaoController;

public class InsereDadosParaTeste {
	private static EntityManagerFactory emf;
	private EntityManager em;

	@BeforeClass
	public static void init() {
		PersistenceUtil.createEntityManagerFactory("argos");
		emf = PersistenceUtil.getEntityManagerFactory();
		ManagedEMContext.bind(emf, emf.createEntityManager());
		System.out.println("init()");
	}

	@AfterClass
	public static void destroy() {
		if (emf != null) {
			emf.close();
		}
	}

	@Before
	public void initEM() {
		em = emf.createEntityManager();
	}

	@Test
	public void testInsereUsuarios() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		// inserindo um usuário Administrador
		Usuario usuario1 = new Usuario();
		usuario1.setMatricula("123456");
		usuario1.setNome("Richard Matthew Stallman");
		usuario1.setEmail("argos@ifpb.edu.br");
		usuario1.setSenha("123456");
		usuario1.setFone("83996193926");
		Date aniversario1 = sdf.parse("1981-06-15");
		usuario1.setDataAniversario(aniversario1);
		usuario1.setStatus(true);
		usuario1.setPerfil("administrador");

		// inserindo um usuário comum
		Usuario usuario2 = new Usuario();
		usuario2.setMatricula("246810");
		usuario2.setNome("Alan Turing");
		usuario2.setEmail("argos@ifpb.edu.br");
		usuario2.setSenha("123456");
		usuario2.setFone("83996193926");
		Date aniversario2 = sdf.parse("1981-06-15");
		usuario2.setDataAniversario(aniversario2);
		usuario2.setStatus(true);
		usuario2.setPerfil("usuario");

		UsuarioDAO udao = new UsuarioDAO(em);
		udao.beginTransaction();
		udao.insert(usuario1);
		udao.insert(usuario2);
		udao.commit();

		
		
		
		
		
		Crime crime = new Crime();
		crime.setTitulo("Crime Teste");
		crime.setHistorico("Histórico teste");
		Date data = sdf.parse("1981-06-15");
		crime.setData(data);

		CrimeDAO crime_dao = new CrimeDAO(em);
		crime_dao.beginTransaction();
		crime_dao.insert(crime);
		crime_dao.commit();

		Investigacao investigacao = new Investigacao();
		investigacao.setTitulo("Titulo de teste");
		investigacao.setDescricao("Descrição teste");
		
		List<Crime> crimes = new ArrayList<Crime>();
		crimes.add(crime);
		investigacao.setCrimes(crimes);
		
		InvestigacaoDAO investigacao_dao = new InvestigacaoDAO(em);
		investigacao_dao.beginTransaction();
		investigacao_dao.insert(investigacao);
		investigacao_dao.commit();
		
		InvestigacaoController investigacao_controller = new InvestigacaoController();
		investigacao_controller.adicionarCrime(crime);

	}
}
