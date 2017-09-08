package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.context.FacesContext;

import br.com.estocandowebjava.domain.ItemEstoque;
import br.com.estocandowebjava.domain.Produto;
import br.com.estocandowebjava.domain.Requisicao;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class ItemEstoqueDAO {
	// DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvar(ItemEstoque ie) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Item_Estoque ");
		sql.append("(Produto_codigo, Requisicao_codigo, quantidade ");
		sql.append("values (?, ?, ?) ");
		

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, ie.getProduto().getCodigo());
		System.out.println("Código do produto escolhido: "+ie.getProduto().getCodigo());
		comando.setLong(2, ie.getRequisicao().getCodigo());
		System.out.println("Código da requisição: "+ie.getRequisicao().getCodigo());
		comando.setDouble(3, ie.getQuantidade());
		System.out.println("Quantidade escolhida: "+ie.getQuantidade());

		comando.executeUpdate();
		System.out.println(sql);
	}
	
	//-----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA LISTAR OS DADOS DA TABELA
	public ArrayList<ItemEstoque> listar() throws SQLException {		
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
			Produto p = new Produto();
			p.setCodigo(resultado.getLong("e.codigo"));
			p.setDescricao(resultado.getString("e.descricao"));
			p.setQuantidade(resultado.getDouble("e.quantidade"));
			p.setValor(resultado.getDouble("e.valor"));
			
			ItemEstoque ie2 = new ItemEstoque();
			ie2.setQuantidade(resultado.getDouble("ie.quantidade"));
			
			ie2.setProduto(p);
			
			Requisicao r = new Requisicao();
			r.setCodigo(resultado.getLong("r.codigo"));
			
			ItemEstoque ie = new ItemEstoque();
			ie.setRequisicao(r);
			
			itens.add(ie2);
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
		System.out.println("Código do produto: "+ie.getProduto().getCodigo());
		comando.setLong(2, ie.getRequisicao().getCodigo());
		System.out.println("Código da requisição "+ie.getRequisicao().getCodigo());
		
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
