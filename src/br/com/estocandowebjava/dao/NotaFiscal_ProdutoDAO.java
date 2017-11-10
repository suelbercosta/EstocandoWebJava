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
		sql.append("START TRANSACTION; ");
			
			StringBuilder sql1 = new StringBuilder();
			sql1.append("insert into Estoque ");
			sql1.append("(descricao, quantidade, unid_med, valor, data_val, data_aquis, ");
			sql1.append("quant_minima, peso, cor, Tipo_Produto_codigo) ");
			sql1.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
			
			StringBuilder sql3 = new StringBuilder();
			sql3.append("SET @cod_nf = ?");
			
			StringBuilder sql4 = new StringBuilder();
			sql4.append("SET @cod_prod = LAST_INSERT_ID(); ");
			
			StringBuilder sql5 = new StringBuilder();
			sql5.append("INSERT INTO NotaFiscal_Produto ");
			sql5.append("(NotaFiscal_codigo, Produto_codigo) ");
			sql5.append("VALUES (@cod_nf, @cod_prod); ");
			
		StringBuilder sql6 = new StringBuilder();
		sql6.append("COMMIT; ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		PreparedStatement comando1 = conexao.prepareStatement(sql1.toString());
		PreparedStatement comando3 = conexao.prepareStatement(sql3.toString());
		PreparedStatement comando4 = conexao.prepareStatement(sql4.toString());
		PreparedStatement comando5 = conexao.prepareStatement(sql5.toString());
		PreparedStatement comando6 = conexao.prepareStatement(sql6.toString());
		
		// INCLUSÃO DOS DADOS NA TABELA PRODUTO
		Produto p = new Produto();
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
		
		// ABSTRAÇÃO DO CÓDIGO DA NOTA FISCAL
		NotaFiscal nf = new NotaFiscal();
		comando3.setLong(1, nf.getCodigo());
		
		// INCLUSÃO DOS DADOS NA TABELA NOTA FISCAL PRODUTO
		comando5.setLong(1, nf_p.getNotafiscal().getCodigo());
		comando5.setLong(2, nf_p.getProduto().getCodigo());

		comando.executeUpdate();
		comando1.executeUpdate();
		comando3.executeUpdate();
		comando4.executeUpdate();
		comando5.executeUpdate();
		comando6.executeUpdate();
		
	}
	
	//-----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA LISTAR OS DADOS DA TABELA
	public ArrayList<NotaFiscal_Produto> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT nf.codigo, nf.numero_nota, e.codigo, e.descricao, e.quantidade,  ");
		sql.append("e.unid_med, e.valor, e.data_val, e.data_aquis, e.quant_minima, e.peso, e.cor ");
		sql.append("FROM NotaFiscal_produto nf_p ");
		sql.append("INNER JOIN Estoque e ON nf_p.Produto_codigo = e.codigo ");
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
			p.setCodigo(resultado.getLong("e.codigo"));
			p.setDescricao(resultado.getString("e.descricao"));
			p.setQuantidade(resultado.getDouble("e.quantidade"));
			p.setUnid_med(resultado.getString("e.unid_med"));
			p.setValor(resultado.getDouble("e.valor"));
			p.setData_val(resultado.getString("e.data_val"));
			p.setData_aquis(resultado.getString("e.data_aquis"));
			p.setQuant_minima(resultado.getDouble("e.quant_minima"));
			p.setPeso(resultado.getDouble("e.peso"));
			p.setCor(resultado.getString("e.cor"));
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
		StringBuilder sql1 = new StringBuilder();
		sql1.append("DELETE FROM NotaFiscal WHERE codigo = ? ");
		StringBuilder sql2 = new StringBuilder();
		sql2.append("DELETE FROM Estoque WHERE codigo = ? ");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, nf_p.getNotafiscal().getCodigo());
		comando.setLong(2, nf_p.getProduto().getCodigo());
		
		PreparedStatement comando1 = conexao.prepareStatement(sql1.toString());
		comando1.setLong(1, nf_p.getNotafiscal().getCodigo());
		
		PreparedStatement comando2 = conexao.prepareStatement(sql2.toString());
		comando2.setLong(1, nf_p.getNotafiscal().getCodigo());
		
		comando.executeUpdate();
		comando1.executeUpdate();
		comando2.executeUpdate();
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
