package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.fabric.FabricCommunicationException;

import br.com.estocandowebjava.domain.Endereco;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class EnderecoDAO {

	// DEFINI��O DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvarEndereco(Endereco e) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Endereco (rua, numero, bairro, cidade, estado) values (?, ?, ?, ?, ?) ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, e.getRua());
		comando.setString(2, e.getNumero());
		comando.setString(3, e.getBairro());
		comando.setString(4, e.getCidade());
		comando.setString(5, e.getEstado());

		comando.executeUpdate();
	}

	// DEFINI��O DO COMANDO SQL PARA EXCLUIR DADOS
	public void excluirEndereco(Endereco e) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from Endereco where codigo = ? ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, e.getCodigo());

		comando.executeUpdate();

	}

	// DEFINI��O DO COMANDO SQL PARA EDITAR OS DADOS
	public void editarEndereco(Endereco e) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update Endereco set rua = ?, numero = ?, bairro = ?, cidade = ?, estado = ? where codigo = ? ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, e.getRua());
		comando.setString(2, e.getNumero());
		comando.setString(3, e.getBairro());
		comando.setString(4, e.getCidade());
		comando.setString(5, e.getEstado());
		comando.setLong(6, e.getCodigo());

		comando.executeUpdate();

	}

	// DEFINI��O DO COMANDO SQL PARA EDITAR OS DADOS
	public Endereco buscarPorCodigo(Endereco e) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select codigo, rua, cidade, estado from Endereco where codigo = ? ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, e.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Endereco retorno = null;

		// CONDI��O QUE DIZ SE A CONSULTA RETORNOU MAIS DE UM VALOR
		if (resultado.next()) {
			retorno = new Endereco();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setRua(resultado.getString("rua"));
			retorno.setCidade(resultado.getString("cidade"));
			retorno.setEstado(resultado.getString("estado"));
		}

		return retorno;
	}

	//CRIA��O DE ARRAYLIST PARA LISTAR TODOS OS EnderecoS
	public ArrayList<Endereco> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from Endereco ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		//CRIA��O DO ARRAYLIST DO TIPO Endereco
		ArrayList<Endereco> lista = new ArrayList<Endereco>();

		//ESTRUTURA DE REPETI��O PARA COLETAR TODOS OS DADOS DA CONSULTA
		while (resultado.next()) {
			Endereco e1 = new Endereco();
			e1.setCodigo(resultado.getLong("codigo"));
			e1.setRua(resultado.getString("rua"));
			e1.setNumero(resultado.getString("numero"));
			e1.setBairro(resultado.getString("bairro"));
			e1.setCidade(resultado.getString("cidade"));
			e1.setEstado(resultado.getString("estado"));

			lista.add(e1);
		}

		return lista;
	}
	
	public ArrayList<Endereco> listarCidades() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from Endereco group by cidade asc");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		//CRIA��O DO ARRAYLIST DO TIPO Endereco
		ArrayList<Endereco> lista = new ArrayList<Endereco>();

		//ESTRUTURA DE REPETI��O PARA COLETAR TODOS OS DADOS DA CONSULTA
		while (resultado.next()) {
			Endereco e1 = new Endereco();
			e1.setCodigo(resultado.getLong("codigo"));
			e1.setRua(resultado.getString("rua"));
			e1.setNumero(resultado.getString("numero"));
			e1.setBairro(resultado.getString("bairro"));
			e1.setCidade(resultado.getString("cidade"));
			e1.setEstado(resultado.getString("estado"));

			lista.add(e1);
		}

		return lista;
	}
	
	//CRIA��O DE ARRAYLIST PARA LISTAR OS DADOS PESQUISADOS POR DESCRI��O
	public ArrayList<Endereco> buscarPorRua(Endereco e) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, Rua FROM Endereco WHERE Rua LIKE ? ");
		sql.append("ORDER BY Rua ASC ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
				Connection conexao = ConexaoFactory.conectar();

				// COMANDO DE PREPARA��O
				PreparedStatement comando = conexao.prepareStatement(sql.toString());
				comando.setString(1, "%" + e.getRua() + "%");
				
				ResultSet resultado = comando.executeQuery();
				
				//CRIA��O DO ARRAYLIST DO TIPO Endereco
				ArrayList<Endereco> lista = new ArrayList<Endereco>();

				//ESTRUTURA DE REPETI��O PARA COLETAR TODOS OS DADOS DA CONSULTA
				while (resultado.next()) {
					Endereco item = new Endereco();
					item.setCodigo(resultado.getLong("codigo"));
					item.setRua(resultado.getString("Rua"));

					lista.add(item);
				}

				return lista;
				
	}

	// CRIA��O DO M�TODO MAIN
	public static void main(String[] args) {
		// TRATAMENTO DE ERRO E LISTAGEM DA CONSULTA NO BANCO DE DADOS POR C�DIGO
		EnderecoDAO cdao = new EnderecoDAO();
		try {
			ArrayList<Endereco> lista = cdao.listar();
			
			for(Endereco c : lista){
				System.out.println("Resultado: " + c);
			}
		}catch(SQLException e) {
			System.out.println("Ocorreu um erro ao tentar listar os Enderecos!");
			e.printStackTrace();
		} 
	}
}
