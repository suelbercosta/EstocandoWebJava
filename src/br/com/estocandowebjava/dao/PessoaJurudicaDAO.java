package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.Endereco;
import br.com.estocandowebjava.domain.Fornecedor;
import br.com.estocandowebjava.domain.Pessoa_Juridica;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class PessoaJurudicaDAO implements FornecedorDAO{
	//DEFINI��O DO COMANDO SQL PARA SALVAR OS DADOS NO BANCO DE DADOS
	@Override
	public void salvar(Fornecedor f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("START TRANSACTION; ");
			sql.append("INSERT INTO Endereco (rua, numero, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?); ");
			sql.append("SET @cod_end = LAST_INSERT_ID(); ");
			sql.append("INSERT INTO Fornecedor (tipo_pessoa, telefone, email, fax, Endereco_codigo) VALUES (?, ?, ?, ?, @cod_end); ");
			sql.append("SET @cod_forn = LAST_INSERT_ID(); ");
			sql.append("INSERT INTO Pessoa_Juridica (cnpj, razao_social, inscricao_estadual, Fornecedor_codigo) VALUES (?, ?, ?, @cod_forn); ");
		sql.append("COMMIT; ");
		
		Connection conexao = ConexaoFactory.conectar(); // COMANDO PARA CONECTAR COM O BANCO DE DADOS
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI � VARI�VEL COMANDO
		
		comando.setString(1, f.getEndereco().getRua());
		System.out.println(f.getEndereco().getRua());
		comando.setString(2, f.getEndereco().getNumero());
		System.out.println(f.getEndereco().getNumero());
		comando.setString(3, f.getEndereco().getBairro());
		System.out.println(f.getEndereco().getBairro());
		comando.setString(4, f.getEndereco().getCidade());
		System.out.println(f.getEndereco().getCidade());
		comando.setString(5, f.getEndereco().getEstado());
		System.out.println(f.getEndereco().getEstado());
		comando.setString(6, f.getTipo_pessoa());
		System.out.println(f.getTipo_pessoa());
		comando.setString(7, f.getTelefone());
		System.out.println(f.getTelefone());
		comando.setString(8, f.getEmail());
		System.out.println(f.getEmail());
		comando.setString(9, f.getFax());
		System.out.println(f.getFax());
		comando.setString(10, f.getPessoajuridica().getCnpj());
		System.out.println(f.getPessoajuridica().getCnpj());
		comando.setString(11, f.getPessoajuridica().getRazao_social());
		System.out.println("Raz�o Social: " + f.getPessoajuridica().getRazao_social());
		comando.setString(12, f.getPessoajuridica().getInscricao_estadual());
		System.out.println("Inscri��o Estadual: " + f.getPessoajuridica().getInscricao_estadual());
		System.out.println(comando);
		
		comando.executeUpdate();
	}
		
	//COMANDO PARA LISTAR OS DADOS DOS FORNECEDORES EM ASSOCIA��O COM A CLASSE ENDERE�O
	@Override
	public ArrayList<Fornecedor> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT f.codigo, f.tipo_pessoa, f.telefone, f.email, f.fax, ");
		sql.append("e.codigo, e.rua, e.numero, e.bairro, e.cidade, e.estado, ");
		sql.append("pj.cnpj, pj.razao_social, pj.inscricao_estadual ");
		sql.append("FROM Fornecedor f ");
		sql.append("INNER JOIN Endereco e ON e.codigo = f.Endereco_codigo ");
		sql.append("INNER JOIN Pessoa_Juridica pj ON pj.Fornecedor_codigo = f.codigo ORDER BY pj.razao_social ");
		
		Connection conexao = ConexaoFactory.conectar(); // COMANDO PARA CONECTAR COM O BANCO DE DADOS
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI � VARI�VEL COMANDO
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fornecedor> itens = new ArrayList<Fornecedor>();
		
		while (resultado.next()) {
			
			Fornecedor f = new Fornecedor();
			Endereco e = new Endereco();
			e.setCodigo(resultado.getLong("e.codigo"));
			e.setRua(resultado.getString("e.rua"));
			e.setNumero(resultado.getString("e.numero"));
			e.setBairro(resultado.getString("e.bairro"));
			e.setCidade(resultado.getString("e.cidade"));
			e.setEstado(resultado.getString("e.estado"));
			f.setEndereco(e);
			
			
			f.setCodigo(resultado.getLong("f.codigo"));
			f.setTipo_pessoa(resultado.getString("f.tipo_pessoa"));
			f.setTelefone(resultado.getString("f.telefone"));
			f.setEmail(resultado.getString("f.email"));
			f.setFax(resultado.getString("f.fax"));
			
			Pessoa_Juridica pj = new Pessoa_Juridica();
			pj.setCnpj(resultado.getString("pj.cnpj"));
			pj.setRazao_social(resultado.getString("pj.razao_social"));
			pj.setInscricao_estadual(resultado.getString("pj.inscricao_estadual"));
			
			f.setPessoajuridica(pj);
			
						
			
			itens.add(f);
		}
		
		return itens;
	}
	
	//COMANDO PARA EXCLUIR OS DADOS DOS FORNECEDORES EM ASSOCIA��O COM A CLASSE ENDERE�O
	@Override
	public void excluir(Fornecedor f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("START TRANSACTION; ");
			sql.append("DELETE pj FROM Pessoa_Juridica pj INNER JOIN Fornecedor f on pj.Fornecedor_codigo = f.codigo WHERE pj.Fornecedor_codigo = ?; ");
			sql.append("DELETE f FROM Fornecedor f INNER JOIN Pessoa_Juridica pj on pj.Fornecedor_codigo = f.codigo WHERE f.codigo = ?; ");
		sql.append("COMMIT; ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI � VARI�VEL COMANDO
		
		comando.setLong(1, f.getCodigo());
		comando.setLong(2, f.getCodigo());
		
		comando.executeUpdate();
	}
	
	//M�TODO PARA EDITAR OS DADOS DOS FORNECEDORES EM ASSOCIA��O COM A CLASSE ENDERE�O E PESSOA JUR�DICA
	@Override
	public void editar(Fornecedor f) throws SQLException {
		
		
		StringBuilder sql = new StringBuilder();
		sql.append("BEGIN TRANSACTION; ");
		
			sql.append("UPDATE Pessoa_Juridica pj ");
			sql.append("INNER JOIN Fornecedor f ON pj.Fornecedor_codigo = f.codigo ");
			sql.append("INNER JOIN Endereco e ON f.Endereco_codigo = e.codigo ");
			sql.append("SET e.rua = ?, e.numero = ?, e.bairro = ?, e.cidade = ?, e.estado, ");
			sql.append("f.tipo_pessoa = ?, f.telefone = ?, f.email = ?, f.fax = ?, ");
			sql.append("pj.cnpj = ?, pj.razao_social = ?, pj.inscricao_estadual = ? ");
			sql.append("WHERE f.codigo = ?; ");

		sql.append("COMMIT; ");
		
		Connection conexao = ConexaoFactory.conectar(); // COMANDO PARA CONECTAR COM O BANCO DE DADOS
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI � VARI�VEL COMANDO
		
		comando.setString(1, f.getEndereco().getRua());
		comando.setString(2, f.getEndereco().getNumero());
		comando.setString(3, f.getEndereco().getBairro());
		comando.setString(4, f.getEndereco().getCidade());
		comando.setString(5, f.getEndereco().getEstado());
		comando.setString(6, f.getTipo_pessoa());
		comando.setString(7, f.getTelefone());
		comando.setString(8, f.getEmail());
		comando.setString(9, f.getFax());
		comando.setString(10, f.getPessoajuridica().getCnpj());
		comando.setString(11, f.getPessoajuridica().getRazao_social());
		comando.setString(12, f.getPessoajuridica().getInscricao_estadual());
		comando.setLong(13, f.getCodigo());
		
		comando.executeUpdate();
	}
}
