package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.fabric.FabricCommunicationException;

import br.com.estocandowebjava.domain.TipoProduto;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class TipoProdutoDAO {

	// DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvarTipoProduto(TipoProduto t) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Tipo_Produto (descricao) values (?) ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, t.getDescricao());

		comando.executeUpdate();
	}

	// DEFINIÇÃO DO COMANDO SQL PARA EXCLUIR DADOS
	public void excluirTipoProduto(TipoProduto t) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from Tipo_Produto where codigo = ? ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, t.getCodigo());

		comando.executeUpdate();

	}

	// DEFINIÇÃO DO COMANDO SQL PARA EDITAR OS DADOS
	public void editarTipoProduto(TipoProduto t) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update Tipo_Produto set descricao = ? where codigo = ? ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, t.getDescricao());
		comando.setLong(2, t.getCodigo());

		comando.executeUpdate();

	}

	// DEFINIÇÃO DO COMANDO SQL PARA EDITAR OS DADOS
	public TipoProduto buscarPorCodigo(TipoProduto t) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select codigo, descricao from Tipo_Produto where codigo = ? ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, t.getCodigo());

		ResultSet resultado = comando.executeQuery();

		TipoProduto retorno = null;

		// CONDIÇÃO QUE DIZ SE A CONSULTA RETORNOU MAIS DE UM VALOR
		if (resultado.next()) {
			retorno = new TipoProduto();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}

		return retorno;
	}

	//CRIAÇÃO DE ARRAYLIST PARA LISTAR TODOS OS CARGOS
	public ArrayList<TipoProduto> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from Tipo_Produto ORDER BY descricao asc ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		//CRIAÇÃO DO ARRAYLIST DO TIPO CARGO
		ArrayList<TipoProduto> lista = new ArrayList<TipoProduto>();

		//ESTRUTURA DE REPETIÇÃO PARA COLETAR TODOS OS DADOS DA CONSULTA
		while (resultado.next()) {
			TipoProduto t1 = new TipoProduto();
			t1.setCodigo(resultado.getLong("codigo"));
			t1.setDescricao(resultado.getString("descricao"));

			lista.add(t1);
		}

		return lista;
	}
	
	//CRIAÇÃO DE ARRAYLIST PARA LISTAR OS DADOS PESQUISADOS POR DESCRIÇÃO
	public ArrayList<TipoProduto> buscarPorDescricao(TipoProduto t) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao FROM Tipo_Produto WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
				Connection conexao = ConexaoFactory.conectar();

				// COMANDO DE PREPARAÇÃO
				PreparedStatement comando = conexao.prepareStatement(sql.toString());
				comando.setString(1, "%" + t.getDescricao() + "%");
				
				ResultSet resultado = comando.executeQuery();
				
				//CRIAÇÃO DO ARRAYLIST DO TIPO CARGO
				ArrayList<TipoProduto> lista = new ArrayList<TipoProduto>();

				//ESTRUTURA DE REPETIÇÃO PARA COLETAR TODOS OS DADOS DA CONSULTA
				while (resultado.next()) {
					TipoProduto item = new TipoProduto();
					item.setCodigo(resultado.getLong("codigo"));
					item.setDescricao(resultado.getString("descricao"));

					lista.add(item);
				}

				return lista;
				
	}

	// CRIAÇÃO DO MÉTODO MAIN
	public static void main(String[] args) {		
		// TRATAMENTO DE ERRO E LISTAGEM DA CONSULTA NO BANCO DE DADOS POR CÓDIGO
		TipoProdutoDAO cdao = new TipoProdutoDAO();
		try {
			ArrayList<TipoProduto> lista = cdao.listar();
			
			for(TipoProduto c : lista){
				System.out.println("Resultado: " + c);
			}
		}catch(SQLException ex) {
			System.out.println("Ocorreu um erro ao tentar listar os tipoprodutos!");
			ex.printStackTrace();
		} 
	}
}
