package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.Endereco;
import br.com.estocandowebjava.domain.Fornecedor;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class FornecedorDAO {
	//DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS NO BANCO DE DADOS
	public void salvar(Fornecedor f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Fornecedor (tipo_pessoa, telefone, email, fax, Endereco_codigo) values (?, ?, ?, ?, ?)");
		
		
		Connection conexao = ConexaoFactory.conectar(); // COMANDO PARA CONECTAR COM O BANCO DE DADOS
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI À VARIÁVEL COMANDO
		
		comando.setLong(1, f.getTipo_pessoa());
		comando.setLong(2, f.getTelefone());
		comando.setString(3, f.getEmail());
		comando.setLong(4, f.getFax());
		comando.setLong(5, f.getEndereco().getCodigo());
		
		comando.executeUpdate();
	}
	
	//COMANDO PARA LISTAR OS DADOS DOS FORNECEDORES EM ASSOCIAÇÃO COM A CLASSE ENDEREÇO
	public ArrayList<Fornecedor> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * ");
		sql.append("from fornecedor f ");
		sql.append("inner join endereco e on e.codigo = f.endereco_codigo ");
		
		Connection conexao = ConexaoFactory.conectar(); // COMANDO PARA CONECTAR COM O BANCO DE DADOS
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI À VARIÁVEL COMANDO
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fornecedor> itens = new ArrayList<Fornecedor>();
		
		while (resultado.next()) {
			Endereco e = new Endereco();
			e.setCodigo(resultado.getLong("e.codigo"));
			e.setRua(resultado.getString("e.rua"));
			e.setNumero(resultado.getString("e.numero"));
			e.setBairro(resultado.getString("e.bairro"));
			e.setCidade(resultado.getString("e.cidade"));
			e.setEstado(resultado.getString("e.estado"));
			
			Fornecedor f = new Fornecedor();
			f.setCodigo(resultado.getLong("f.codigo"));
			f.setTipo_pessoa(resultado.getLong("f.tipo_pessoa"));
			f.setTelefone(resultado.getLong("f.telefone"));
			f.setEmail(resultado.getString("f.email"));
			f.setFax(resultado.getLong("f.fax"));
			f.setEndereco(e);
			
			itens.add(f);
		}
		
		return itens;
	}
	
	//COMANDO PARA EXCLUIR OS DADOS DOS FORNECEDORES EM ASSOCIAÇÃO COM A CLASSE ENDEREÇO
	public void excluir(Fornecedor f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from Fornecedor ");
		sql.append("where codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI À VARIÁVEL COMANDO
		
		comando.setLong(1, f.getCodigo());
		
		comando.executeUpdate();
	}
	
	public void editar(Fornecedor f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE Fornecedor ");
		sql.append("SET tipo_pessoa = ?, telefone = ?, email = ?, fax = ?, endereco_codigo = ? ");
		sql.append("WHERE codigo = ?");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI À VARIÁVEL COMANDO
		
		comando.setLong(1, f.getTipo_pessoa());
		comando.setLong(2, f.getTelefone());
		comando.setString(3, f.getEmail());
		comando.setLong(4, f.getFax());
		comando.setLong(5, f.getEndereco().getCodigo());
		comando.setLong(6, f.getCodigo());
		
		comando.executeUpdate();
	}
}
