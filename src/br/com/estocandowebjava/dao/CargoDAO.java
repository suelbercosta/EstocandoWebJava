package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.fabric.FabricCommunicationException;

import br.com.estocandowebjava.domain.Cargo;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class CargoDAO {

// DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvarCargo(Cargo c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Cargo (descricao, salario, permissao) values (?, ?, ?) ");

// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, c.getDescricao());
		comando.setDouble(2, c.getSalario());
		comando.setLong(3, c.getPermissao());

		comando.executeUpdate();
	}

// DEFINIÇÃO DO COMANDO SQL PARA EXCLUIR DADOS
	public void excluirCargo(Cargo c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from cargo where cod_cargo = ? ");

// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, c.getCodigo());

		comando.executeUpdate();

	}

// DEFINIÇÃO DO COMANDO SQL PARA EDITAR OS DADOS
	public void editarCargo(Cargo c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update Cargo set descricao = ? where cod_cargo = ? ");

// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, c.getDescricao());
		comando.setLong(2, c.getCodigo());

		comando.executeUpdate();

	}

// DEFINIÇÃO DO COMANDO SQL PARA EDITAR OS DADOS
	public Cargo buscarPorCodigo(Cargo c) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select cod_cargo, descricao from Cargo where cod_cargo = ? ");

// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, c.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Cargo retorno = null;

// CONDIÇÃO QUE DIZ SE A CONSULTA RETORNOU MAIS DE UM VALOR
		if (resultado.next()) {
			retorno = new Cargo();
			retorno.setCodigo(resultado.getLong("cod_cargo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}

		return retorno;
	}

//CRIAÇÃO DO MÉTODO MAIN
	public static void main(String[] args) {
		/*
		 * CÓDIGO INSERÇÃO DOS DADOS NA TABELA 
		 * Cargo c1 = new Cargo();
		 * c1.setDescricao("Gestor de Transportes"); 
		 * c1.setSalario(6000.00);
		 * c1.setPermissao(0L);
		 * 
		 * Cargo c2 = new Cargo(); 
		 * c2.setDescricao("Analista de Patrimônio");
		 * c2.setSalario(1730.00); 
		 * c2.setPermissao(0L);
		 * 
		 * CargoDAO cdao = new CargoDAO(); 
		 * // TRATAMENTO DE ERRO DA PERSISTÊNCIA NO BANCO DE DADOS 
		 * try { 
		 * 		cdao.salvarCargo(c1); 
		 * 		cdao.salvarCargo(c2);
		 * 		System.out.println("O(s) cargo(a) foi(ram) salvo(s) com sucesso."); 
		 * } catch (SQLException e) {
		 * 		System.out.println("Ocorreu um erro ao tentar salvar os dados.");
		 * 		e.printStackTrace(); 
		 * }
		 */

		 /*
		 * CÓDIGO INSERÇÃO DOS DADOS NA TABELA 
		 * Cargo c1 = new Cargo();
		 * c1.setCodigo(3L);
		 * 
		 * Cargo c2 = new Cargo(); c2.setCodigo(7L);
		 * 
		 * CargoDAO cdao = new CargoDAO();
		 * 
		 * // TRATAMENTO DE ERRO DE EXCLUSÃO NO BANCO DE DADOS 
		 * try {
		 * 		cdao.excluirCargo(c1); 
		 * 		cdao.excluirCargo(c2);
		 * 		System.out.println("Os cargos foram excluidos com sucesso."); 
		 * } catch (SQLException e) {
		 *		System.out.println("Ocorreu um erro ao tentar excluir o(s) cargo(s)."); 
		 *		e.printStackTrace(); 
		 * } 
		 */
		 
		 /* 
		 * CÓDIGO ALTERAÇÃO DOS DADOS NA TABELA 
		 * Cargo c1 = new Cargo();
		 * c1.setCodigo(2L); 
		 * c1.setDescricao("Analista de Expedição");
		 * 
		 * CargoDAO cdao = new CargoDAO(); 
		 * 
		 * // TRATAMENTO DE ERRO DA ALTERAÇÃO NO BANCO DE DADOS
		 * try { cdao.editarCargo(c1);
		 * 		System.out.println("O cargo foi editado com sucesso."); 
		 * } catch (SQLException e) {
		 * 		System.out.println("Ocorreu um erro na alteração do cargo.");
		 * 		e.printStackTrace(); 
		 * }
		 */

//CÓDIGO PARA CONSULTA NO BANCO DE DADOS
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
		}
		
	}
}
