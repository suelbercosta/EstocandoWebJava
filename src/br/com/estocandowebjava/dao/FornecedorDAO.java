package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.Fornecedor;
import br.com.estocandowebjava.domain.Pessoa_Fisica;
import br.com.estocandowebjava.domain.Pessoa_Juridica;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public abstract class FornecedorDAO {
	
	//COMANDO PARA TRAZER TODOS OS FORNECEDORES EM UMA COLUNA COM SQL
	public ArrayList<Fornecedor> fornecedores() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT Fornecedor_codigo AS Codigo, nome AS Fornecedor ");
		sql.append("FROM pessoa_fisica ");
		sql.append("UNION ");
		sql.append("SELECT Fornecedor_codigo AS Codigo, razao_social AS Fornecedor ");
		sql.append("FROM pessoa_juridica ");
		sql.append("ORDER BY fornecedor ");
		
		Connection conexao = ConexaoFactory.conectar(); // COMANDO PARA CONECTAR COM O BANCO DE DADOS
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI À VARIÁVEL COMANDO
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fornecedor> itens = new ArrayList<Fornecedor>();
		
		while (resultado.next()) {
			
			Fornecedor f = new Fornecedor();
			f.setCodigo(resultado.getLong("Codigo"));
			Pessoa_Fisica pf = new Pessoa_Fisica();
			Pessoa_Juridica pj = new Pessoa_Juridica();
			pf.setNome(resultado.getString("Fornecedor"));
			pj.setRazao_social(resultado.getString("Fornecedor"));
			f.setPessoafisica(pf);
			f.setPessoajuridica(pj);
			
			itens.add(f);
		}
		
		return itens;
	}
	
	//DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS NO BANCO DE DADOS
	public void salvar(Fornecedor f) throws SQLException {
	}
	
	//COMANDO PARA LISTAR OS DADOS DOS FORNECEDORES EM ASSOCIAÇÃO COM A CLASSE ENDEREÇO
	public ArrayList<Fornecedor> listar() throws SQLException{
		return null;
	}
	
	//COMANDO PARA EXCLUIR OS DADOS DOS FORNECEDORES EM ASSOCIAÇÃO COM A CLASSE ENDEREÇO
	public void excluir(Fornecedor f) throws SQLException {
	}
	
	//MÉTODO PARA EDITAR OS DADOS DOS FORNECEDORES EM ASSOCIAÇÃO COM A CLASSE ENDEREÇO E PESSOA JURÍDICA
	public void editar(Fornecedor f) throws SQLException {
	}
}
