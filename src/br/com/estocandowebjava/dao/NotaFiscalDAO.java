package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.Fornecedores;
import br.com.estocandowebjava.domain.NotaFiscal;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class NotaFiscalDAO {

	// DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvar(NotaFiscal nf) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into NotaFiscal ");
		sql.append("(numero_nota, Fornecedor_codigo) ");
		sql.append("values (?, ?) ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, nf.getNumero_nota());
		System.out.println(nf.getNumero_nota());
		comando.setLong(2, nf.getFornecedor().getCodigo());
		System.out.println(nf.getFornecedor().getCodigo());
		
		comando.executeUpdate();
	}
	
	//-----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA LISTAR OS DADOS DA TABELA	
	public ArrayList<NotaFiscal> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nf.codigo, nf.numero_nota, pj.razao_social as fornecedor ");
		sql.append("FROM notafiscal nf, pessoa_juridica pj ");
		sql.append("WHERE nf.fornecedor_codigo = pj.fornecedor_codigo ");
		sql.append("group by nf.codigo union ");
		sql.append("SELECT nf.codigo, nf.numero_nota, pf.nome as fornecedor ");
		sql.append("FROM notafiscal nf, pessoa_fisica pf ");
		sql.append("WHERE nf.fornecedor_codigo = pf.fornecedor_codigo ");
		sql.append("group by nf.codigo ");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<NotaFiscal> itens = new ArrayList<NotaFiscal>();
		
		while(resultado.next()) {
			NotaFiscal nf = new NotaFiscal();
			nf.setCodigo(resultado.getLong("codigo"));
			nf.setNumero_nota(resultado.getLong("numero_nota"));
			
			Fornecedores f = new Fornecedores();
			f.setFornecedor(resultado.getString("fornecedor"));
			nf.setFornecedor(f);
			
			itens.add(nf);
		}
		
		return itens;

	}
	
	//----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA EXCLUIR DADOS DA TABELA
	public void excluir(NotaFiscal nf) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM NotaFiscal WHERE codigo = ?");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, nf.getCodigo());
		
		comando.executeUpdate();
	}
	
	//----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA EDITAR DADOS DA TABELA
	public void editar(NotaFiscal nf) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE NotaFiscal SET ");
		sql.append("numero_nota = ?, Fornecedor_codigo = ? ");
		sql.append("WHERE codigo = ? ");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();
		
		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setDouble(1, nf.getNumero_nota());
		comando.setLong(2, nf.getFornecedor().getCodigo());
		comando.setLong(3, nf.getCodigo());

		comando.executeUpdate();
	}

}
