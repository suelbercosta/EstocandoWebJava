package br.com.estocandowebjava.teste;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.estocandowebjava.dao.FuncionarioDAO;
import br.com.estocandowebjava.domain.Cargo;
import br.com.estocandowebjava.domain.Endereco;
import br.com.estocandowebjava.domain.Funcionario;
import br.com.estocandowebjava.domain.Setor;

public class FuncionarioDAOTeste {
	@Test
	@Ignore
	//MÉTODO SALVAR
	public void salvar() throws SQLException{
		Funcionario f = new Funcionario();
		f.setNome("Suelber Costa da Paz");
		f.setCpf("99999999999");
		f.setRg("7654321");
		f.setData_nasc("30-08-1987");
		f.setSexo("M");
		f.setCtps("15358");
		f.setData_admissao("02-09-2013");
		f.setPis("13575522455");
		f.setTipo_sang("O+");
		
		Cargo c = new Cargo();
		c.setCodigo(1L);
		f.setCargo(c);
		
		f.setLogin("suelbercosta");
		f.setSenha("2415sdsdI&");
		
		Setor s = new Setor();
		s.setCodigo(2L);
		f.setSetor(s);
	
		Endereco e = new Endereco();
		e.setCodigo(3L);
		f.setEndereco(e);
		
		
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.salvar(f);
	}
	
	@Test
	//MÉTODO PARA LISTAR OS DADOS
	public void listar() throws SQLException {
		FuncionarioDAO  dao = new FuncionarioDAO();
		ArrayList<Funcionario> lista = dao.listar();
		
		for(Funcionario f : lista) {
			System.out.println("Código: " + f.getMatricula());
			System.out.println("Nome: " + f.getNome());
			System.out.println("Data Nasc.: " + f.getData_nasc());
			System.out.println("Cargo: " + f.getCargo().getDescricao());
			System.out.println("Salário: " + f.getCargo().getSalario());
			System.out.println();
		}
	}
	
	@Test
	@Ignore
	//MÉTODO PARA EXCLUIR OS DADOS
	public void excluir() throws SQLException {
		Funcionario f = new Funcionario();
		f.setMatricula(3L);
		
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.excluir(f);
	}
	
	@Test
	@Ignore
	//MÉTODO PARA EDITAR OS DADOS
	public void editar() throws SQLException {
		Funcionario f = new Funcionario();
		f.setMatricula(4L);
		f.setNome("Rutylene da Silva Oliveira Costa");
		f.setCpf("888.888.888-88");
		f.setRg("7.654.321");
		f.setData_nasc("23-07-1988");
		f.setSexo("F");
		f.setCtps("17854");
		f.setData_admissao("14-11-2015");
		f.setPis("19512535845");
		f.setTipo_sang("O+");
		
		Cargo c = new Cargo();
		c.setCodigo(3L);
		f.setCargo(c);
		
		f.setLogin("rutyleneoliveira");
		f.setSenha("a6%g4#gan0");
		
		Setor s = new Setor();
		s.setCodigo(1L);
		f.setSetor(s);
	
		Endereco e = new Endereco();
		e.setCodigo(1L);
		f.setEndereco(e);
		
		
		FuncionarioDAO dao = new FuncionarioDAO();
		dao.editar(f);
	}
	
}
