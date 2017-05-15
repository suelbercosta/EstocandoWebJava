package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.fabric.FabricCommunicationException;

import br.com.estocandowebjava.domain.Setor;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class SetorDAO {

	// DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvarSetor(Setor s) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Setor (descricao, andar) values (?, ?) ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, s.getDescricao());
		comando.setString(2, s.getAndar());

		comando.executeUpdate();
	}

	// DEFINIÇÃO DO COMANDO SQL PARA EXCLUIR DADOS
	public void excluirSetor(Setor s) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from Setor where cod_setor = ? ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, s.getCodigo());

		comando.executeUpdate();

	}

	// DEFINIÇÃO DO COMANDO SQL PARA EDITAR OS DADOS
	public void editarSetor(Setor s) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update Setor set descricao = ? where cod_setor = ? ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, s.getDescricao());
		comando.setLong(2, s.getCodigo());

		comando.executeUpdate();

	}

	// DEFINIÇÃO DO COMANDO SQL PARA EDITAR OS DADOS
	public Setor buscarPorCodigo(Setor s) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select cod_setor, descricao from Setor where cod_setor = ? ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, s.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Setor retorno = null;

		// CONDIÇÃO QUE DIZ SE A CONSULTA RETORNOU MAIS DE UM VALOR
		if (resultado.next()) {
			retorno = new Setor();
			retorno.setCodigo(resultado.getLong("cod_setor"));
			retorno.setDescricao(resultado.getString("descricao"));
		}

		return retorno;
	}

	//CRIAÇÃO DE ARRAYLIST PARA LISTAR TODOS OS CARGOS
	public ArrayList<Setor> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from Setor ORDER BY descricao asc ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		//CRIAÇÃO DO ARRAYLIST DO TIPO CARGO
		ArrayList<Setor> lista = new ArrayList<Setor>();

		//ESTRUTURA DE REPETIÇÃO PARA COLETAR TODOS OS DADOS DA CONSULTA
		while (resultado.next()) {
			Setor s1 = new Setor();
			s1.setCodigo(resultado.getLong("cod_setor"));
			s1.setDescricao(resultado.getString("descricao"));
			s1.setAndar(resultado.getString("andar"));

			lista.add(s1);
		}

		return lista;
	}
	
	//CRIAÇÃO DE ARRAYLIST PARA LISTAR OS DADOS PESQUISADOS POR DESCRIÇÃO
	public ArrayList<Setor> buscarPorDescricao(Setor s) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cod_setor, descricao FROM Setor WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
				Connection conexao = ConexaoFactory.conectar();

				// COMANDO DE PREPARAÇÃO
				PreparedStatement comando = conexao.prepareStatement(sql.toString());
				comando.setString(1, "%" + s.getDescricao() + "%");
				
				ResultSet resultado = comando.executeQuery();
				
				//CRIAÇÃO DO ARRAYLIST DO TIPO CARGO
				ArrayList<Setor> lista = new ArrayList<Setor>();

				//ESTRUTURA DE REPETIÇÃO PARA COLETAR TODOS OS DADOS DA CONSULTA
				while (resultado.next()) {
					Setor item = new Setor();
					item.setCodigo(resultado.getLong("cod_setor"));
					item.setDescricao(resultado.getString("descricao"));

					lista.add(item);
				}

				return lista;
				
	}

	// CRIAÇÃO DO MÉTODO MAIN
	public static void main(String[] args) {
		/*
		 * CÓDIGO INSERÇÃO DOS DADOS NA TABELA Cargo c1 = new Cargo();
		 * c1.setDescricao("Gestor de Transportes"); c1.setSalario(6000.00);
		 * c1.setPermissao(0L);
		 * 
		 * Cargo c2 = new Cargo(); c2.setDescricao("Analista de Patrimônio");
		 * c2.setSalario(1730.00); c2.setPermissao(0L);
		 * 
		 * CargoDAO cdao = new CargoDAO(); // TRATAMENTO DE ERRO DA PERSISTÊNCIA
		 * NO BANCO DE DADOS try { cdao.salvarCargo(c1); cdao.salvarCargo(c2);
		 * System.out.println("O(s) cargo(a) foi(ram) salvo(s) com sucesso."); }
		 * catch (SQLException e) {
		 * System.out.println("Ocorreu um erro ao tentar salvar os dados.");
		 * e.printStackTrace(); }
		 */

		/*
		 * CÓDIGO INSERÇÃO DOS DADOS NA TABELA Cargo c1 = new Cargo();
		 * c1.setCodigo(3L);
		 * 
		 * Cargo c2 = new Cargo(); c2.setCodigo(7L);
		 * 
		 * CargoDAO cdao = new CargoDAO();
		 * 
		 * // TRATAMENTO DE ERRO DE EXCLUSÃO NO BANCO DE DADOS try {
		 * cdao.excluirCargo(c1); cdao.excluirCargo(c2);
		 * System.out.println("Os cargos foram excluidos com sucesso."); } catch
		 * (SQLException e) {
		 * System.out.println("Ocorreu um erro ao tentar excluir o(s) cargo(s)."
		 * ); e.printStackTrace(); }
		 */

		/*
		 * CÓDIGO ALTERAÇÃO DOS DADOS NA TABELA Cargo c1 = new Cargo();
		 * c1.setCodigo(2L); c1.setDescricao("Analista de Expedição");
		 * 
		 * CargoDAO cdao = new CargoDAO();
		 * 
		 * // TRATAMENTO DE ERRO DA ALTERAÇÃO NO BANCO DE DADOS try {
		 * cdao.editarCargo(c1);
		 * System.out.println("O cargo foi editado com sucesso."); } catch
		 * (SQLException e) {
		 * System.out.println("Ocorreu um erro na alteração do cargo.");
		 * e.printStackTrace(); }
		 */

		/*
		// CÓDIGO PARA CONSULTA NO BANCO DE DADOS
		Cargo c1 = new Cargo();
		c1.setCodigo(1L);

		Cargo c2 = new Cargo();
		c2.setCodigo(9L);

		CargoDAO cdao = new CargoDAO();

		// TRATAMENTO DE ERRO DA CONSULTA NO BANCO DE DADOS
		try {
			Cargo f3 = cdao.buscarPorCodigo(c1);
			Cargo f4 = cdao.buscarPorCodigo(c2);
			System.out.println("Resultado 1: " + f3);
			System.out.println("Resultado 2: " + f4);
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar perquisar um dos cargos.");
			e.printStackTrace();
		}*/
		
		
		
		// TRATAMENTO DE ERRO E LISTAGEM DA CONSULTA NO BANCO DE DADOS POR CÓDIGO
		SetorDAO cdao = new SetorDAO();
		try {
			ArrayList<Setor> lista = cdao.listar();
			
			for(Setor s : lista){
				System.out.println("Resultado: " + s);
			}
		}catch(SQLException e) {
			System.out.println("Ocorreu um erro ao tentar listar os cargos!");
			e.printStackTrace();
		} 
		
		// TRATAMENTO DE ERRO E LISTAGEM DA CONSULTA NO BANCO DE DADOS POR DESCRIÇÃO
		/*Cargo c1 = new Cargo();
		c1.setDescricao("de");
		
		CargoDAO cdao = new CargoDAO();
		
		try {
			ArrayList<Cargo> lista = cdao.buscarPorDescricao(c1);
			
			for(Cargo c : lista){
				System.out.println("Resultado: " + c);
			}
		}catch(SQLException e) {
			System.out.println("Ocorreu um erro ao tentar perquisar um cargo.");
			e.printStackTrace();
		}*/

	}
}