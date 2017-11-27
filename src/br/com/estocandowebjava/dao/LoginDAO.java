package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.estocandowebjava.domain.Login;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class LoginDAO {
	
	public void validarLogin(Login l) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT f.nome, f.login, f.senha, c.permissao ");
		sql.append("FROM Funcionario f inner join Cargo c ");
		sql.append("on c.cod_cargo = f.Cargo_cod_cargo ");
		sql.append("WHERE f.login = ? AND f.senha = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		System.out.println(l.getFuncionario().getLogin());
		comando.setString(1, l.getFuncionario().getLogin());
		comando.setString(2, l.getFuncionario().getSenha());
		
		comando.executeQuery();
	}
	
	
}
