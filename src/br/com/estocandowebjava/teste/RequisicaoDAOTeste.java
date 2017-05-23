package br.com.estocandowebjava.teste;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.estocandowebjava.dao.RequisicaoDAO;
import br.com.estocandowebjava.domain.Funcionario;
import br.com.estocandowebjava.domain.Requisicao;

public class RequisicaoDAOTeste {
	@Test
	@Ignore
	//M�TODO SALVAR
	public void salvar() throws SQLException{
		Requisicao r = new Requisicao();
		r.setData("15/05/2017");
		
		Funcionario f1 = new Funcionario();
		f1.setMatricula(7L);
		r.setAlmoxarife(f1);
		
		Funcionario f2 = new Funcionario();
		f2.setMatricula(5L);
		r.setRequisitante(f2);	
		
		RequisicaoDAO dao = new RequisicaoDAO();
		dao.salvar(r);
	}
	
	@Test
	//M�TODO PARA LISTAR OS DADOS
	public void listar() throws SQLException {
		RequisicaoDAO  dao = new RequisicaoDAO();
		ArrayList<Requisicao> lista = dao.listar();
		
		for(Requisicao r : lista) {
			System.out.println("C�digo: " + r.getCodigo());
			System.out.println("Data: " + r.getData());
			System.out.println("Almoxarife: " + r.getAlmoxarife().getNome());
			System.out.println("Requisitante " + r.getRequisitante().getNome());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	//M�TODO PARA EXCLUIR OS DADOS
	public void excluir() throws SQLException {
		Requisicao r = new Requisicao();
		r.setCodigo(3L);
		
		RequisicaoDAO dao = new RequisicaoDAO();
		dao.excluir(r);
	}
	
	@Test
	@Ignore
	//M�TODO PARA EDITAR OS DADOS
	public void editar() throws SQLException {
		Requisicao r = new Requisicao();
		r.setData("20/05/2017");
		
		Funcionario f1 = new Funcionario();
		f1.setMatricula(5L);
		r.setAlmoxarife(f1);
		
		Funcionario f2 = new Funcionario();
		f2.setMatricula(1L);
		r.setRequisitante(f2);	
		
		RequisicaoDAO dao = new RequisicaoDAO();
		dao.salvar(r);
	}
	
}