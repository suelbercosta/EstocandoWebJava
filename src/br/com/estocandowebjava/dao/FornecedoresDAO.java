package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.Fornecedores;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class FornecedoresDAO {
	public ArrayList<Fornecedores> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT Fornecedor_codigo AS codigo, nome AS fornecedor ");
		sql.append("FROM pessoa_fisica ");
		sql.append("UNION ");
		sql.append("SELECT Fornecedor_codigo AS codigo, razao_social AS fornecedor ");
		sql.append("FROM pessoa_juridica ");
		sql.append("ORDER BY fornecedor ");
		
		Connection conexao = ConexaoFactory.conectar(); // COMANDO PARA CONECTAR COM O BANCO DE DADOS
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI À VARIÁVEL COMANDO
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fornecedores> itens = new ArrayList<Fornecedores>();
		
		while (resultado.next()) {
			
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getLong("codigo"));
			f.setFornecedor(resultado.getString("fornecedor"));
			
			itens.add(f);
		}
		
		return itens;
	}
}
