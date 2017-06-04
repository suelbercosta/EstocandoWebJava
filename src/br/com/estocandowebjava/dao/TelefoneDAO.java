package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.Funcionario;
import br.com.estocandowebjava.domain.Telefone;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class TelefoneDAO {
	
	// DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvar(Telefone t) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Telefone (Funcionario_matricula, telefone) values (?, ?) ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, t.getFuncionario().getMatricula());
		comando.setString(2, t.getTelefone());

		comando.executeUpdate();
	}

	// DEFINIÇÃO DO COMANDO SQL PARA EXCLUIR DADOS
	public void excluir(Telefone t) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from Telefone where codigo = ? ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, t.getCodigo());

		comando.executeUpdate();

	}

	// DEFINIÇÃO DO COMANDO SQL PARA EDITAR OS DADOS
	public void editar(Telefone t) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update Telefone set Funcionario_matricula = ?, telefone = ? where codigo = ? ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, t.getFuncionario().getMatricula());
		comando.setString(2, t.getTelefone());
		comando.setLong(3, t.getCodigo());

		comando.executeUpdate();

	}

	//CRIAÇÃO DE ARRAYLIST PARA LISTAR TODOS OS TelefoneS
	public ArrayList<Telefone> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT t.codigo, t.telefone, f.nome ");
		sql.append("FROM Telefone t ");
		sql.append("INNER JOIN Funcionario f ON f.matricula = t.Funcionario_matricula ");
		sql.append("ORDER BY f.nome asc ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		//CRIAÇÃO DO ARRAYLIST DO TIPO Telefone
		ArrayList<Telefone> lista = new ArrayList<Telefone>();

		//ESTRUTURA DE REPETIÇÃO PARA COLETAR TODOS OS DADOS DA CONSULTA
		while (resultado.next()) {
			Telefone t1 = new Telefone();
			t1.setCodigo(resultado.getLong("t.codigo"));
			t1.setTelefone(resultado.getString("t.telefone"));
			
			Funcionario f = new Funcionario();
			f.setNome(resultado.getString("f.nome"));
			t1.setFuncionario(f);

			lista.add(t1);
		}

		return lista;
	}

}
