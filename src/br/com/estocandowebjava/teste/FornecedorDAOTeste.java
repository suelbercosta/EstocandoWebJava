package br.com.estocandowebjava.teste;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.estocandowebjava.dao.PessoaJurudicaDAO;
import br.com.estocandowebjava.domain.Endereco;
import br.com.estocandowebjava.domain.Fornecedor;

public class FornecedorDAOTeste {
	@Test
	@Ignore
	public void salvar() throws SQLException{
		Fornecedor f = new Fornecedor();
		f.setTipo_pessoa("pf");
		f.setTelefone("(81) 9114-6771");
		f.setEmail("rutyleneoliveira@gmail.com");
		f.setFax("(81) 3626-3626");
		
		Endereco e = new Endereco();
		e.setCodigo(2L);
		
		f.setEndereco(e);
		
		PessoaJurudicaDAO dao = new PessoaJurudicaDAO();
		dao.salvar(f);
		
	}
	
	@Test
	@Ignore
	public void listar() throws SQLException {
		PessoaJurudicaDAO dao = new PessoaJurudicaDAO();
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
		
		PessoaJurudicaDAO dao = new PessoaJurudicaDAO();
		dao.excluir(f);
		System.out.println("Dados excluidos com sucesso!");
	}
	
	@Test
	public void editar() throws SQLException {
		Fornecedor f = new Fornecedor();
		f.setCodigo(7L);
		f.setTipo_pessoa("pj");
		f.setTelefone("(81) 9512-3654");
		f.setEmail("testedeemail@email.com");
		f.setFax("(81) 3626-3626");
		
		Endereco e = new Endereco();
		e.setCodigo(1L);
		
		f.setEndereco(e);
		
		PessoaJurudicaDAO dao = new PessoaJurudicaDAO();
		dao.editar(f);
	}
}
