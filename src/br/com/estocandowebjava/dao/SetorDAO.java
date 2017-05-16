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

	// DEFINI��O DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvarSetor(Setor s) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Setor (descricao, andar) values (?, ?) ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, s.getDescricao());
		comando.setString(2, s.getAndar());

		comando.executeUpdate();
	}

	// DEFINI��O DO COMANDO SQL PARA EXCLUIR DADOS
	public void excluirSetor(Setor s) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from Setor where cod_setor = ? ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, s.getCodigo());

		comando.executeUpdate();

	}

	// DEFINI��O DO COMANDO SQL PARA EDITAR OS DADOS
	public void editarSetor(Setor s) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update Setor set descricao = ?, andar = ? where cod_setor = ? ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, s.getDescricao());
		comando.setString(2, s.getAndar());
		comando.setLong(3, s.getCodigo());

		comando.executeUpdate();

	}

	// DEFINI��O DO COMANDO SQL PARA EDITAR OS DADOS
	public Setor buscarPorCodigo(Setor s) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select cod_setor, descricao from Setor where cod_setor = ? ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, s.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Setor retorno = null;

		// CONDI��O QUE DIZ SE A CONSULTA RETORNOU MAIS DE UM VALOR
		if (resultado.next()) {
			retorno = new Setor();
			retorno.setCodigo(resultado.getLong("cod_setor"));
			retorno.setDescricao(resultado.getString("descricao"));
		}

		return retorno;
	}

	//CRIA��O DE ARRAYLIST PARA LISTAR TODOS OS CARGOS
	public ArrayList<Setor> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from Setor ORDER BY descricao asc ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		//CRIA��O DO ARRAYLIST DO TIPO CARGO
		ArrayList<Setor> lista = new ArrayList<Setor>();

		//ESTRUTURA DE REPETI��O PARA COLETAR TODOS OS DADOS DA CONSULTA
		while (resultado.next()) {
			Setor s1 = new Setor();
			s1.setCodigo(resultado.getLong("cod_setor"));
			s1.setDescricao(resultado.getString("descricao"));
			s1.setAndar(resultado.getString("andar"));

			lista.add(s1);
		}

		return lista;
	}
	
	//CRIA��O DE ARRAYLIST PARA LISTAR OS DADOS PESQUISADOS POR DESCRI��O
	public ArrayList<Setor> buscarPorDescricao(Setor s) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT cod_setor, descricao FROM Setor WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
				Connection conexao = ConexaoFactory.conectar();

				// COMANDO DE PREPARA��O
				PreparedStatement comando = conexao.prepareStatement(sql.toString());
				comando.setString(1, "%" + s.getDescricao() + "%");
				
				ResultSet resultado = comando.executeQuery();
				
				//CRIA��O DO ARRAYLIST DO TIPO CARGO
				ArrayList<Setor> lista = new ArrayList<Setor>();

				//ESTRUTURA DE REPETI��O PARA COLETAR TODOS OS DADOS DA CONSULTA
				while (resultado.next()) {
					Setor item = new Setor();
					item.setCodigo(resultado.getLong("cod_setor"));
					item.setDescricao(resultado.getString("descricao"));

					lista.add(item);
				}

				return lista;
				
	}

	// CRIA��O DO M�TODO MAIN
	public static void main(String[] args) {
		// TRATAMENTO DE ERRO E LISTAGEM DA CONSULTA NO BANCO DE DADOS POR C�DIGO
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
	}
}