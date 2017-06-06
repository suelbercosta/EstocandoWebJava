package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.NotaFiscal;
import br.com.estocandowebjava.domain.NotaFiscal_Produto;
import br.com.estocandowebjava.domain.Produto;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class NotaFiscal_ProdutoDAO {

	// DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvar(NotaFiscal_Produto nf_p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO NotaFiscal_Produto ");
		sql.append("(NotaFiscal_codigo, Produto_codigo ");
		sql.append("VALUES (?, ?) ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, nf_p.getNotafiscal().getCodigo());
		comando.setLong(2, nf_p.getProduto().getCodigo());

		comando.executeUpdate();
	}
	
	//-----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA LISTAR OS DADOS DA TABELA
	public ArrayList<NotaFiscal_Produto> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM NotaFiscal_produto nf_p INNER JOIN Produto p ");
		sql.append("ON nf_p.Produto_codigo = p.codigo ");
		sql.append("INNER JOIN NotaFiscal nf ON nf_p.NotaFiscal_codigo = nf.codigo ");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<NotaFiscal_Produto> itens = new ArrayList<NotaFiscal_Produto>();
		
		while(resultado.next()) {
			NotaFiscal_Produto nf_p = new NotaFiscal_Produto();
			
			NotaFiscal nf = new NotaFiscal();
			nf.setCodigo(resultado.getLong("nf.codigo"));
			nf.setNumeroNota(resultado.getLong("nf.numero_nota"));
			nf_p.setNotafiscal(nf);
			
			Produto p = new Produto();
			p.setCodigo(resultado.getLong("p.codigo"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setQuantidade(resultado.getDouble("p.quantidade"));
			p.setUnid_med(resultado.getString("p.unid_med"));
			p.setValor(resultado.getDouble("p.valor"));
			p.setData_val(resultado.getString("p.data_val"));
			p.setData_aquis(resultado.getString("p.data_aquis"));
			p.setQuant_minima(resultado.getDouble("p.quant_minima"));
			p.setPeso(resultado.getDouble("p.peso"));
			p.setCor(resultado.getString("p.cor"));
			nf_p.setProduto(p);
			
			itens.add(nf_p);
		}
		
		return itens;

	}
	
	//----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA EXCLUIR DADOS DA TABELA
	public void excluir(NotaFiscal_Produto nf_p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM NotaFiscal_Produto WHERE NotaFiscal_codigo = ? AND Produto_codigo = ? ");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, nf_p.getNotafiscal().getCodigo());
		comando.setLong(2, nf_p.getProduto().getCodigo());
		
		comando.executeUpdate();
	}
	
	//----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA EDITAR DADOS DA TABELA
	public void editar(NotaFiscal_Produto nf_p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE NotaFiscal_Produto SET ");
		sql.append("NotaFiscal_codigo = ?, Produto_codigo = ? ");
		sql.append("WHERE NotaFiscal_codigo = ? AND Produto_codigo = ? ");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();
		
		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, nf_p.getNotafiscal().getCodigo());
		comando.setLong(2, nf_p.getProduto().getCodigo());

		comando.executeUpdate();
	}

}
