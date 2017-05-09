package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		sql.append("select cod_setor, descricao from Cargo where cod_setor = ? ");

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

	// CRIAÇÃO DO MÉTODO MAIN
	public static void main(String[] args) {
			
			/* //CÓDIGO INSERÇÃO DOS DADOS NA TABELA 
			 Setor s1 = new Setor();
			 s1.setDescricao("Gestor de Transportes"); 
			 s1.setAndar("Térreo");
			 
			 Setor s2 = new Setor(); 
			 s2.setDescricao("Analista de Patrimônio");
			 s2.setAndar("1º Andar");
			 
			 SetorDAO sdao = new SetorDAO(); 
			 // TRATAMENTO DE ERRO DA PERSISTÊNCIA NO BANCO DE DADOS 
			 try { 
			 		sdao.salvarSetor(s1); 
			 		sdao.salvarSetor(s2);
			 		System.out.println("O(s) setor(es) foi(ram) salvo(s) com sucesso."); 
			 } catch (SQLException e) {
			 		System.out.println("Ocorreu um erro ao tentar salvar os dados.");
			 		e.printStackTrace(); 
			 } */
			 

			 
			 //CÓDIGO INSERÇÃO DOS DADOS NA TABELA 
			 /*Setor s1 = new Setor();
			 s1.setCodigo(3L);
			 
			 Setor s2 = new Setor(); 
			 s2.setCodigo(7L);
			 
			 SetorDAO sdao = new SetorDAO();
			 
			 // TRATAMENTO DE ERRO DE EXCLUSÃO NO BANCO DE DADOS 
			 try {
			 		sdao.excluirSetor(s1); 
			 		sdao.excluirSetor(s2);
			 		System.out.println("O(s) setor(es) foi(ram) excluido(s) com sucesso."); 
			 } catch (SQLException e) {
			 	System.out.println("Ocorreu um erro ao tentar excluir o(s) setor(es)."); 
			 	e.printStackTrace(); 
			 } */
			 
			 
			 /*
			 //CÓDIGO ALTERAÇÃO DOS DADOS NA TABELA 
			 Setor s1 = new Setor();
			 s1.setCodigo(2L); 
			 s1.setDescricao("Andar 1");
			 
			 SetorDAO sdao = new SetorDAO(); 
			 
			 // TRATAMENTO DE ERRO DA ALTERAÇÃO NO BANCO DE DADOS
			 try { sdao.editarSetor(s1);
			 		System.out.println("O setor foi editado com sucesso."); 
			 } catch (SQLException e) {
			 		System.out.println("Ocorreu um erro na alteração do setor.");
			 		e.printStackTrace(); 
			 }*/
			 

	//CÓDIGO PARA CONSULTA NO BANCO DE DADOS
			Setor s1 = new Setor();
			s1.setCodigo(1L);
			
			Setor s2 = new Setor();
			s2.setCodigo(9L);
			
			SetorDAO sdao = new SetorDAO();
			
	// TRATAMENTO DE ERRO DA CONSULTA NO BANCO DE DADOS
			try {
				Setor f3 = sdao.buscarPorCodigo(s1);
				Setor f4 = sdao.buscarPorCodigo(s2);
				System.out.println("Resultado 1: " + f3);
				System.out.println("Resultado 2: " + f4);
			} catch (SQLException e) {
				System.out.println("Ocorreu um erro ao tentar perquisar um dos cargos.");
				e.printStackTrace();
			}
			
		}

}
