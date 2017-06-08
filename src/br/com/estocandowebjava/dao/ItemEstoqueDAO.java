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
	private Long cod;
	private Double quant;

	// DEFINI��O DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvar(ItemEstoque ie) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Item_Estoque ");
		sql.append("(Requisicao_codigo, Produto_codigo, quantidade ");
		sql.append("values (?, ?, ?) ");
		

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, ie.getRequisicao().getCodigo());
		comando.setLong(2, ie.getProduto().getCodigo());
		comando.setDouble(3, ie.getQuantidade());

		comando.executeUpdate();
	}
	
	//-----------------------------------------------------------------
	
	//DEFINI��O DO COMANDO PARA LISTAR OS DADOS DA TABELA
	public ArrayList<ItemEstoque> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM Item_Estoque ie ");
		sql.append("INNER JOIN Produto p ON p.codigo = ie.Produto_codigo ");
		sql.append("INNER JOIN Requisicao r ON r.codigo = ie.Requisicao_codigo ");
		
		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
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
	
	//DEFINI��O DO COMANDO PARA EXCLUIR DADOS DA TABELA
	public void excluir(ItemEstoque ie) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM Item_Estoque WHERE Produto_codigo = ? AND Requisicao_codigo = ?");
		
		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, ie.getProduto().getCodigo());
		comando.setLong(2, ie.getRequisicao().getCodigo());
		
		comando.executeUpdate();
	}
	
	//----------------------------------------------------------------
	
	//DEFINI��O DO COMANDO PARA EDITAR DADOS DA TABELA
	public void editar(ItemEstoque ie) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE Item_Estoque SET ");
		sql.append("Requisicao_codigo = ?, Produto_codigo = ?, quantidade = ? ");
		sql.append("WHERE Produto_codigo = ? AND Requisicao_codigo = ? ");
		
		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();
		
		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, ie.getRequisicao().getCodigo());
		comando.setLong(2, ie.getProduto().getCodigo());
		comando.setDouble(3, ie.getQuantidade());
		comando.setLong(4, ie.getProduto().getCodigo());
		comando.setLong(5, ie.getRequisicao().getCodigo());

		comando.executeUpdate();
	}
	
	//M�TODO PARA VALIDAR A QUANTIDADE SOLICITADA
	public ArrayList<Produto> validarQuantidade() throws SQLException {
		//CRIA��O DAS VARI�VEIS LOCAIS
		Produto p = new Produto();
		cod = null;
		quant = null;
		
		//ATRIBUI��O DOS VALORES DIGITADOS �S VARI�VEIS LOCAIS
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO Produto (codigo) VALUES (?) ");
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, cod);
		
		//CONSULTA NO BANCO PARA COMPARAR COM O VALOR DIGITADO
		StringBuilder sql1 = new StringBuilder();
		sql1.append("SELECT quantidade FROM Produto WHERE codigo = '" + cod + "' ");

		PreparedStatement comando1 = conexao.prepareStatement(sql1.toString());
		
		ResultSet resultado = comando1.executeQuery();
		System.out.println(sql1);
		System.out.println(comando1);
		
		ArrayList<Produto> lista = new ArrayList<Produto>();
		
		while (resultado.next()) {
			p.setQuantidade(resultado.getDouble("quantidade"));
			
			lista.add(p);
		}
		
		return lista;
	}
}
