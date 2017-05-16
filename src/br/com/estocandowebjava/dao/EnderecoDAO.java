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
		sql.append("select * from Endereco ORDER BY rua asc ");

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
		/*
		 * C�DIGO INSER��O DOS DADOS NA TABELA Endereco e1 = new Endereco();
		 * e1.setRua("Gestor de Transportes"); e1.setSalario(6000.00);
		 * e1.setPermissao(0L);
		 * 
		 * Endereco c2 = new Endereco(); c2.setRua("Analista de Patrim�nio");
		 * c2.setSalario(1730.00); c2.setPermissao(0L);
		 * 
		 * EnderecoDAO cdao = new EnderecoDAO(); // TRATAMENTO DE ERRO DA PERSIST�NCIA
		 * NO BANCO DE DADOS try { cdao.salvarEndereco(e1); cdao.salvarEndereco(c2);
		 * System.out.println("O(s) Endereco(a) foi(ram) salvo(s) com sucesso."); }
		 * catch (SQLException e) {
		 * System.out.println("Ocorreu um erro ao tentar salvar os dados.");
		 * e.printStackTrace(); }
		 */

		/*
		 * C�DIGO INSER��O DOS DADOS NA TABELA Endereco e1 = new Endereco();
		 * e1.setCodigo(3L);
		 * 
		 * Endereco c2 = new Endereco(); c2.setCodigo(7L);
		 * 
		 * EnderecoDAO cdao = new EnderecoDAO();
		 * 
		 * // TRATAMENTO DE ERRO DE EXCLUS�O NO BANCO DE DADOS try {
		 * cdao.excluirEndereco(e1); cdao.excluirEndereco(c2);
		 * System.out.println("Os Enderecos foram excluidos com sucesso."); } catch
		 * (SQLException e) {
		 * System.out.println("Ocorreu um erro ao tentar excluir o(s) Endereco(s)."
		 * ); e.printStackTrace(); }
		 */

		/*
		 * C�DIGO ALTERA��O DOS DADOS NA TABELA Endereco e1 = new Endereco();
		 * e1.setCodigo(2L); e1.setRua("Analista de Expedi��o");
		 * 
		 * EnderecoDAO cdao = new EnderecoDAO();
		 * 
		 * // TRATAMENTO DE ERRO DA ALTERA��O NO BANCO DE DADOS try {
		 * cdao.editarEndereco(e1);
		 * System.out.println("O Endereco foi editado com sucesso."); } catch
		 * (SQLException e) {
		 * System.out.println("Ocorreu um erro na altera��o do Endereco.");
		 * e.printStackTrace(); }
		 */

		/*
		// C�DIGO PARA CONSULTA NO BANCO DE DADOS
		Endereco e1 = new Endereco();
		e1.setCodigo(1L);

		Endereco c2 = new Endereco();
		c2.setCodigo(9L);

		EnderecoDAO cdao = new EnderecoDAO();

		// TRATAMENTO DE ERRO DA CONSULTA NO BANCO DE DADOS
		try {
			Endereco f3 = cdao.buscarPorCodigo(e1);
			Endereco f4 = cdao.buscarPorCodigo(c2);
			System.out.println("Resultado 1: " + f3);
			System.out.println("Resultado 2: " + f4);
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar perquisar um dos Enderecos.");
			e.printStackTrace();
		}*/
		
		
		
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
		
		// TRATAMENTO DE ERRO E LISTAGEM DA CONSULTA NO BANCO DE DADOS POR DESCRI��O
		/*Endereco e1 = new Endereco();
		e1.setRua("de");
		
		EnderecoDAO cdao = new EnderecoDAO();
		
		try {
			ArrayList<Endereco> lista = cdao.buscarPorRua(e1);
			
			for(Endereco c : lista){
				System.out.println("Resultado: " + c);
			}
		}catch(SQLException e) {
			System.out.println("Ocorreu um erro ao tentar perquisar um Endereco.");
			e.printStackTrace();
		}*/

	}
}
