package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.estocandowebjava.domain.Funcionario;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class LoginDAO {
	
	public void validarLogin(Funcionario login, Funcionario senha, Funcionario permissao) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT f.nome, f.login, f.senha, c.permissao ");
		sql.append("FROM Funcionario f inner join Cargo c ");
		sql.append("on c.cod_cargo = f.Cargo_cod_cargo ");
		sql.append("WHERE f.login = ? AND f.senha = ? c.permissao != 0; ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, login.getLogin());
		comando.setString(2, senha.getSenha());
		comando.setLong(3, permissao.getCargo().getPermissao());
		
		ResultSet resultado = comando.executeQuery();
		
		String log = resultado.getString("login.login");
		String sen = resultado.getString("senha.senha");
		Long perm = resultado.getLong("permissao.permissao");
		
		if (log == "" && sen == "" ) {
			System.out.println("Você não é cadastrado para utilizar o sistema, verifique suas permissões.");
		}
		else {
			if (perm == 1) {
				
			}
			if (perm == 2) {
				
			}
		}
		
		
		

	}
	
	
}
