package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.ItemEstoque;
import br.com.estocandowebjava.domain.Produto;
import br.com.estocandowebjava.domain.Requisicao;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class ItemEstoqueDAO {
	// DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvar(ItemEstoque ie) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Item_Estoque ");
		sql.append("(Requisicao_codigo, Produto_codigo, quantidade ");
		sql.append("values (?, ?, ?) ");
		

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, ie.getRequisicao().getCodigo());
		System.out.println(ie.getRequisicao().getCodigo());
		comando.setLong(2, ie.getProduto().getCodigo());
		System.out.println(ie.getProduto().getCodigo());
		comando.setDouble(3, ie.getQuantidade());
		System.out.println(ie.getQuantidade());

		comando.executeUpdate();
		System.out.println(comando);
	}
	
	//-----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA LISTAR OS DADOS DA TABELA
	public ArrayList<ItemEstoque> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM Item_Estoque ie ");
		sql.append("INNER JOIN Produto p ON p.codigo = ie.Produto_codigo ");
		sql.append("INNER JOIN Requisicao r ON r.codigo = ie.Requisicao_codigo ");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<ItemEstoque> itens = new ArrayList<ItemEstoque>();
		
		while(resultado.next()) {
			ItemEstoque ie = new ItemEstoque();
			ie.setQuantidade(resultado.getDouble("ie.quantidade"));
			
			Produto p = new Produto();
			p.setCodigo(resultado.getLong("p.codigo"));
			ie.setProduto(p);
			
			Requisicao r = new Requisicao();
			r.setCodigo(resultado.getLong("r.codigo"));
			ie.setRequisicao(r);
			
			itens.add(ie);
		}
		
		return itens;

	}
	
	//----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA EXCLUIR DADOS DA TABELA
	public void excluir(ItemEstoque ie) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM Item_Estoque WHERE Produto_codigo = ? AND Requisicao_codigo = ?");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, ie.getProduto().getCodigo());
		comando.setLong(2, ie.getRequisicao().getCodigo());
		
		comando.executeUpdate();
	}
	
	//----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA EDITAR DADOS DA TABELA
	public void editar(ItemEstoque ie) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE Item_Estoque SET ");
		sql.append("Requisicao_codigo = ?, Produto_codigo = ?, quantidade = ? ");
		sql.append("WHERE Produto_codigo = ? AND Requisicao_codigo = ? ");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();
		
		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, ie.getRequisicao().getCodigo());
		comando.setLong(2, ie.getProduto().getCodigo());
		comando.setDouble(3, ie.getQuantidade());
		comando.setLong(4, ie.getProduto().getCodigo());
		comando.setLong(5, ie.getRequisicao().getCodigo());

		comando.executeUpdate();
	}
	
}
