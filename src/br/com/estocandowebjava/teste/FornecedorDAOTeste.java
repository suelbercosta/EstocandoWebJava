package br.com.estocandowebjava.teste;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.estocandowebjava.dao.FornecedorDAO;
import br.com.estocandowebjava.domain.Endereco;
import br.com.estocandowebjava.domain.Fornecedor;

public class FornecedorDAOTeste {
	@Test
	@Ignore
	public void salvar() throws SQLException{
		Fornecedor f = new Fornecedor();
		f.setTipo_pessoa(2L);
		f.setTelefone(991146771L);
		f.setEmail("rutyleneoliveira@gmail.com");
		f.setFax(36263626L);
		
		Endereco e = new Endereco();
		e.setCodigo(2L);
		
		f.setEndereco(e);
		
		FornecedorDAO dao = new FornecedorDAO();
		dao.salvar(f);
		
	}
	
	@Test
	@Ignore
	public void listar() throws SQLException {
		FornecedorDAO dao = new FornecedorDAO();
		ArrayList<Fornecedor> lista = dao.listar();
		
		for(Fornecedor p : lista) {
			System.out.println("DADOS DO FORNECEDOR");
			System.out.println("     Código do Fornecedor: " + p.getCodigo());
			System.out.println("     Tipo Pessoa: " + p.getTipo_pessoa());
			System.out.println("     Telefone: " + p.getTelefone());
			System.out.println("     Email: " + p.getEmail());
			System.out.println("     Fax: " + p.getFax());
			System.out.println("ENDEREÇO DO FORNECEDOR");
			System.out.println("     Rua: " + p.getEndereco().getRua());
			System.out.println("     Número: " + p.getEndereco().getNumero());
			System.out.println("     Bairro: " + p.getEndereco().getBairro());
			System.out.println("     Cidade: " + p.getEndereco().getCidade());
			System.out.println("     Estado: " + p.getEndereco().getEstado());
			System.out.println("------------------------------------");
		}
	}
	
	@Test
	@Ignore
	public void excluir() throws SQLException {
		Fornecedor f = new Fornecedor();
		f.setCodigo(5L);
		
		FornecedorDAO dao = new FornecedorDAO();
		dao.excluir(f);
		System.out.println("Dados excluidos com sucesso!");
	}
	
	@Test
	public void editar() throws SQLException {
		Fornecedor f = new Fornecedor();
		f.setCodigo(7L);
		f.setTipo_pessoa(1L);
		f.setTelefone(995123654L);
		f.setEmail("testedeemail@email.com");
		f.setFax(36311100L);
		
		Endereco e = new Endereco();
		e.setCodigo(1L);
		
		f.setEndereco(e);
		
		FornecedorDAO dao = new FornecedorDAO();
		dao.editar(f);
	}
}
