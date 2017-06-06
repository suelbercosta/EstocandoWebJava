package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.Fornecedor;
import br.com.estocandowebjava.domain.NotaFiscal;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class NotaFiscalDAO {

	// DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvar(NotaFiscal nf) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into NotaFiscal ");
		sql.append("(numero_nota, Fornecedor_codigo ");
		sql.append("values (?, ?) ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, nf.getCodigo());
		comando.setLong(2, nf.getFornecedor().getCodigo());

		comando.executeUpdate();
	}
	
	//-----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA LISTAR OS DADOS DA TABELA
	public ArrayList<NotaFiscal> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM NotaFiscal nf inner join Fornecedor f ");
		sql.append("on f.codigo = nf.Fornecedor_codigo ");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<NotaFiscal> itens = new ArrayList<NotaFiscal>();
		
		while(resultado.next()) {
			NotaFiscal nf = new NotaFiscal();
			nf.setCodigo(resultado.getLong("nf.codigo"));
			nf.setNumeroNota(resultado.getLong("nf.numero_nota"));
			
			Fornecedor f = new Fornecedor();
			f.setCodigo(resultado.getLong("f.codigo"));
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
		comando.setLong(1, nf.getNumeroNota());
		comando.setLong(2, nf.getFornecedor().getCodigo());
		comando.setLong(3, nf.getCodigo());

		comando.executeUpdate();
	}

}
