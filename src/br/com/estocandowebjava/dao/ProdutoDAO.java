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

	// DEFINI��O DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvar(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("START TRANSACTION; ");
			
			StringBuilder sql1 = new StringBuilder();
			sql1.append("insert into Estoque ");
			sql1.append("(descricao, quantidade, unid_med, valor, data_val, data_aquis, ");
			sql1.append("quant_minima, peso, cor, Tipo_Produto_codigo) ");
			sql1.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ");
			
			StringBuilder sql2 = new StringBuilder();
			sql2.append("SET @cod_nf = 1;");
			
			StringBuilder sql3 = new StringBuilder();
			sql3.append("SET @cod_prod = LAST_INSERT_ID(); ");
			
			StringBuilder sql4 = new StringBuilder();
			sql4.append("INSERT INTO NotaFiscal_Produto ");
			sql4.append("(NotaFiscal_codigo, Produto_codigo) ");
			sql4.append("VALUES (@cod_nf, @cod_prod); ");
			
		StringBuilder sql5 = new StringBuilder();
		sql5.append("COMMIT; ");
		
		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();
		
		// COMANDOS DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		PreparedStatement comando1 = conexao.prepareStatement(sql1.toString());
		PreparedStatement comando2 = conexao.prepareStatement(sql2.toString());
		PreparedStatement comando3 = conexao.prepareStatement(sql3.toString());
		PreparedStatement comando4 = conexao.prepareStatement(sql4.toString());
		PreparedStatement comando5 = conexao.prepareStatement(sql5.toString());
		
		// INCLUS�O DOS DADOS NA TABELA PRODUTO
		comando1.setString(1, p.getDescricao()); 
		comando1.setDouble(2, p.getQuantidade()); 
		comando1.setString(3, p.getUnid_med()); 
		comando1.setDouble(4, p.getValor()); 
		comando1.setString(5, p.getData_val()); 
		comando1.setString(6, p.getData_aquis()); 
		comando1.setDouble(7, p.getQuant_minima()); 
		comando1.setDouble(8, p.getPeso()); 
		comando1.setString(9, p.getCor()); 
		comando1.setLong(10, p.getTipo_produto().getCodigo()); 
		
		comando.executeUpdate();
		comando1.executeUpdate();
		comando2.executeUpdate();
		comando3.executeUpdate();
		comando4.executeUpdate();
		comando5.executeUpdate();
	}
	
	//-----------------------------------------------------------------
	
	//DEFINI��O DO COMANDO PARA LISTAR OS DADOS DA TABELA
	public ArrayList<Produto> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM Estoque p inner join Tipo_Produto t ");
		sql.append("on t.codigo = p.tipo_produto_codigo order by p.descricao ");
		
		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
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
			t.setCodigo(resultado.getLong("t.codigo"));
			t.setDescricao(resultado.getString("t.descricao"));
			p.setTipo_produto(t);
			
			itens.add(p);
		}
		
		return itens;

	}
	
	//----------------------------------------------------------------
	
	//DEFINI��O DO COMANDO PARA EXCLUIR DADOS DA TABELA
	public void excluir(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM Estoque WHERE codigo = ?");
		
		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());
		
		comando.executeUpdate();
	}
	
	//----------------------------------------------------------------
	
	//DEFINI��O DO COMANDO PARA EDITAR DADOS DA TABELA
	public void editar(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE Estoque SET ");
		sql.append("descricao = ?, quantidade = ?, unid_med = ?, valor = ?, data_val = ?, ");
		sql.append("data_aquis = ?, quant_minima = ?, peso = ?, cor = ?, Tipo_Produto_codigo = ? ");
		sql.append("WHERE codigo = ? ");
		
		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();
		
		// COMANDO DE PREPARA��O
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
	}

}
