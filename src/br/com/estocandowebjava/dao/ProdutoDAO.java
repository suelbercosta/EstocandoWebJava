package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.Produto;
import br.com.estocandowebjava.domain.TipoProduto;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class ProdutoDAO {

	// DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvar(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Estoque ");
		sql.append("(descricao, quantidade, unid_med, valor, data_val, data_aquis, ");
		sql.append("quant_minima, peso, cor, Tipo_Produto_codigo) ");
		sql.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getQuantidade());
		comando.setString(3, p.getUnid_med());
		comando.setDouble(4, p.getValor());
		comando.setString(5, p.getData_val());
		comando.setString(6, p.getData_aquis());
		comando.setDouble(7, p.getQuant_minima());
		comando.setDouble(8, p.getPeso());
		comando.setString(9, p.getCor());
		comando.setLong(10, p.getTipo_produto().getCodigo());

		comando.executeUpdate();
	}
	
	//-----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA LISTAR OS DADOS DA TABELA
	public ArrayList<Produto> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM Estoque p inner join Tipo_Produto t ");
		sql.append("on t.codigo = p.tipo_produto_codigo order by p.descricao ");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Produto> itens = new ArrayList<Produto>();
		
		while(resultado.next()) {
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
			
			TipoProduto t = new TipoProduto();
			t.setDescricao(resultado.getString("t.descricao"));
			p.setTipo_produto(t);
			
			itens.add(p);
		}
		
		return itens;

	}
	
	//----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA EXCLUIR DADOS DA TABELA
	public void excluir(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM Estoque WHERE codigo = ?");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());
		
		comando.executeUpdate();
	}
	
	//----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA EDITAR DADOS DA TABELA
	public void editar(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE Estoque SET ");
		sql.append("descricao = ?, quantidade = ?, unid_med = ?, valor = ?, data_val = ?, ");
		sql.append("data_aquis = ?, quant_minima = ?, peso = ?, cor = ?, tipo_produto = ? ");
		sql.append("WHERE codigo = ? ");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();
		
		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getQuantidade());
		comando.setString(3, p.getUnid_med());
		comando.setDouble(4, p.getValor());
		comando.setString(5, p.getData_val());
		comando.setString(6, p.getData_aquis());
		comando.setDouble(7, p.getQuant_minima());
		comando.setDouble(8, p.getPeso());
		comando.setString(9, p.getCor());
		comando.setLong(10, p.getTipo_produto().getCodigo());
		comando.setLong(11, p.getCodigo());

		comando.executeUpdate();

		comando.executeUpdate();
	}

}
