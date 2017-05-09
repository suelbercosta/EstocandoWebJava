package br.com.estocandowebjava.factoty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	//Declaração de variáreis de conexão...
	private static final String USUARIO = "root";
	private static final String SENHA = "";
	private static final String URL = "jdbc:mysql://localhost:3306/estocando";

	
	// Conexão com o banco de dados MySql...
	public static Connection conectar() throws SQLException{
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
	
	//Tratamento de exceção...
	public static void main(String[] args) {
		try {
			Connection conexao = ConexaoFactory.conectar();
			System.out.println("Conexão realizada com sucesso!");
		}catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Não foi possível realizar a conexão!");
		}
	}
}
