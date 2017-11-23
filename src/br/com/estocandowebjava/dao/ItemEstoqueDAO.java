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
	//DEFINIÇÃO DO COMANDO PARA FILTRAR OS PRODUTOS DA REQUISIÇÃO
	public ArrayList<ItemEstoque> filtrar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT e.codigo, e.descricao, e.quantidade, e.valor, ie.quantidade, r.codigo ");
		sql.append("FROM Item_Estoque ie ");
		sql.append("INNER JOIN Estoque e ON e.codigo = ie.Produto_codigo ");
		sql.append("INNER JOIN Requisicao r ON r.codigo = ie.Requisicao_codigo ");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<ItemEstoque> itens = new ArrayList<ItemEstoque>();
		
		while(resultado.next()) {
			Requisicao r = new Requisicao();
			r.setCodigo(resultado.getLong("r.codigo"));
			
			Produto p = new Produto();
			
			p.setCodigo(resultado.getLong("e.codigo"));
			p.setDescricao(resultado.getString("e.descricao"));
			p.setQuantidade(resultado.getDouble("e.quantidade"));
			p.setValor(resultado.getDouble("e.valor"));
			
			ItemEstoque ie2 = new ItemEstoque();
			ie2.setQuantidade(resultado.getDouble("ie.quantidade"));
			
			ie2.setProduto(p);
			
			
			
			ItemEstoque ie = new ItemEstoque();
			ie.setRequisicao(r);
			
			itens.add(ie2);
		}
		return itens;
	}
	
	// DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvar(ItemEstoque ie) throws SQLException {
		Long cod = ie.getProduto().getCodigo();
		Long req = ie.getRequisicao().getCodigo();
		Double quant = ie.getQuantidade();
		
		System.out.println("Código do produto: "+cod);
		System.out.println("Código da requisição: "+req);
		System.out.println("Quantidade requisitada: "+quant);
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO Item_Estoque ");
		sql.append("(Produto_codigo, Requisicao_codigo, quantidade) ");
		sql.append("values (?, ?, ?) ");
		
		StringBuilder sql2 = new StringBuilder();
		sql2.append("UPDATE Estoque SET ");
		sql2.append("quantidade = quantidade - ? ");
		sql2.append("WHERE codigo = ? ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		PreparedStatement comando2 = conexao.prepareStatement(sql2.toString());
		
		comando.setLong(1, cod);
		comando.setLong(2, req);
		comando.setDouble(3, quant);
		
		comando2.setDouble(1, quant);
		comando2.setLong(2, cod);
		
		comando.executeUpdate();
		comando2.executeUpdate();
		
	}
	
	//-----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA LISTAR OS DADOS DA TABELA
	public ArrayList<ItemEstoque> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT r.codigo, i.Requisicao_codigo, e.codigo, e.descricao, r.data, i.quantidade ");
		sql.append("FROM item_estoque i, estoque e, requisicao r  ");
		sql.append("WHERE i.Requisicao_codigo = r.codigo AND i.Produto_codigo = e.codigo ");
		sql.append("order by e.descricao ");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();
		
		ArrayList<ItemEstoque> itens = new ArrayList<ItemEstoque>();
		
		while(resultado.next()) {
			ItemEstoque ie2 = new ItemEstoque();
			ie2.setQuantidade(resultado.getDouble("i.quantidade"));
			
			Requisicao r = new Requisicao();
			r.setCodigo(resultado.getLong("r.codigo"));
			r.setData(resultado.getString("r.data"));
			ie2.setRequisicao(r);
			
			Produto p = new Produto();
			p.setCodigo(resultado.getLong("e.codigo"));
			p.setDescricao(resultado.getString("e.descricao"));
			ie2.setProduto(p);
			
			itens.add(ie2);
		}
		return itens;

	}
	
	//----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA EXCLUIR DADOS DA TABELA
	public void excluir(ItemEstoque ie) throws SQLException {
		Long cod_prod = ie.getProduto().getCodigo();
		Long req = ie.getRequisicao().getCodigo();
		Double quant = ie.getQuantidade();
		
		System.out.println("Código da requisição "+req);
		System.out.println("Código do produto: "+cod_prod);
		System.out.println("Quantidade: "+quant);
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM Item_Estoque WHERE Requisicao_codigo = ? AND Produto_codigo = ? ");
		
		StringBuilder sql2 = new StringBuilder();
		sql2.append("UPDATE Estoque SET quantidade = quantidade + ? WHERE codigo = ? ");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		PreparedStatement comando2 = conexao.prepareStatement(sql2.toString());
		
		comando.setLong(1, req);
		comando.setLong(2, cod_prod);
		
		comando2.setDouble(1, quant);
		comando2.setLong(2, cod_prod);
		
		comando.executeUpdate();
		comando2.executeUpdate();
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
