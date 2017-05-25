package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.Cargo;
import br.com.estocandowebjava.domain.Funcionario;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class LoginDAO {
	
	public void validarLogin() throws SQLException{
		Funcionario func = new Funcionario();
		Cargo cargo = new Cargo();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT f.nome, f.login, f.senha, c.permissao ");
		sql.append("FROM Funcionario f inner join Cargo c ");
		sql.append("on c.cod_cargo = f.Cargo_cod_cargo ");
		sql.append("WHERE c.permissao = 0; ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Funcionario> itens = new ArrayList<Funcionario>();
		
		func.setLogin(resultado.getString("func.login"));
		func.setSenha(resultado.getString("func.senha"));
		cargo.setPermissao(resultado.getLong("cargo.permissao"));
		func.setCargo(cargo);
		
		itens.add(func);
		

	}
	
	
}
