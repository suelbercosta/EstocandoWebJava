package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.Fornecedor;
import br.com.estocandowebjava.domain.NotaFiscal;
import br.com.estocandowebjava.domain.Pessoa_Fisica;
import br.com.estocandowebjava.domain.Pessoa_Juridica;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class NotaFiscalDAO {

	// DEFINI��O DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvar(NotaFiscal nf) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into NotaFiscal ");
		sql.append("(numero_nota, Fornecedor_codigo ");
		sql.append("values (?, ?) ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, nf.getCodigo());
		comando.setLong(2, nf.getFornecedor().getCodigo());

		comando.executeUpdate();
	}
	
	//-----------------------------------------------------------------
	
	//DEFINI��O DO COMANDO PARA LISTAR OS DADOS DA TABELA
	/*public ArrayList<NotaFiscal> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, numero_nota, Fornecedor_codigo ");
		sql.append("FROM notafiscal nf inner join Fornecedor f ");
		sql.append("on f.codigo = nf.Fornecedor_codigo ");
		
		StringBuilder sql2 = new StringBuilder();
		sql2.append("SELECT Fornecedor_codigo AS Codigo, nome AS Fornecedor ");
		sql2.append("FROM pessoa_fisica UNION ");
		sql2.append("SELECT Fornecedor_codigo AS Codigo, razao_social AS Fornecedor ");
		sql2.append("FROM pessoa_juridica order by fornecedor ");
		
		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
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

	}*/
	
	public ArrayList<NotaFiscal> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nf.codigo, nf.numero_nota, pj.razao_social as Fornecedor ");
		sql.append("FROM notafiscal nf, pessoa_juridica pj ");
		sql.append("WHERE nf.fornecedor_codigo = pj.fornecedor_codigo ");
		sql.append("group by nf.codigo union ");
		sql.append("SELECT nf.codigo, nf.numero_nota, pf.nome as Fornecedor ");
		sql.append("FROM notafiscal nf, pessoa_fisica pf ");
		sql.append("WHERE nf.fornecedor_codigo = pf.fornecedor_codigo ");
		sql.append("group by nf.codigo ");
		
		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<NotaFiscal> itens = new ArrayList<NotaFiscal>();
		
		while(resultado.next()) {
			NotaFiscal nf = new NotaFiscal();
			nf.setCodigo(resultado.getLong("codigo"));
			nf.setNumeroNota(resultado.getLong("numero_nota"));
			
			Pessoa_Juridica pj = new Pessoa_Juridica();
			pj.setRazao_social(resultado.getString("pj.razao_social"));
			nf.setFornecedor(pj);
			
			Pessoa_Fisica pf = new Pessoa_Fisica();
			pf.setNome(resultado.getString("pf.nome"));
			nf.setFornecedor(pf);
			
			itens.add(nf);
		}
		
		return itens;

	}
	
	//----------------------------------------------------------------
	
	//DEFINI��O DO COMANDO PARA EXCLUIR DADOS DA TABELA
	public void excluir(NotaFiscal nf) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM NotaFiscal WHERE codigo = ?");
		
		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, nf.getCodigo());
		
		comando.executeUpdate();
	}
	
	//----------------------------------------------------------------
	
	//DEFINI��O DO COMANDO PARA EDITAR DADOS DA TABELA
	public void editar(NotaFiscal nf) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE NotaFiscal SET ");
		sql.append("numero_nota = ?, Fornecedor_codigo = ? ");
		sql.append("WHERE codigo = ? ");
		
		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();
		
		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, nf.getNumeroNota());
		comando.setLong(2, nf.getFornecedor().getCodigo());
		comando.setLong(3, nf.getCodigo());

		comando.executeUpdate();
	}

}
