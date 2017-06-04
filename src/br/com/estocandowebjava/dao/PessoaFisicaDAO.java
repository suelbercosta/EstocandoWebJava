package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.Endereco;
import br.com.estocandowebjava.domain.Fornecedor;
import br.com.estocandowebjava.domain.Pessoa_Fisica;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class PessoaFisicaDAO implements FornecedorDAO{
	//DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS NO BANCO DE DADOS
	@Override
	public void salvar(Fornecedor f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("START TRANSACTION; ");
			sql.append("INSERT INTO Endereco (rua, numero, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?); ");
			sql.append("SET @cod_end = LAST_INSERT_ID();");
			sql.append("INSERT INTO Fornecedor (tipo_pessoa, telefone, email, fax, Endereco_codigo) VALUES (?, ?, ?, ?, @cod_end); ");
			sql.append("SET @cod_forn = LAST_INSERT_ID();");
			sql.append("INSERT INTO Pessoa_Fisica (cpf, nome, rg, data_nasc, Fornecedor_codigo) VALUES (?, ?, ?, ?, @cod_forn); ");
		sql.append("COMMIT; ");
		
		Connection conexao = ConexaoFactory.conectar(); // COMANDO PARA CONECTAR COM O BANCO DE DADOS
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI À VARIÁVEL COMANDO
		
		comando.setString(1, f.getEndereco().getRua());
		comando.setString(2, f.getEndereco().getNumero());
		comando.setString(3, f.getEndereco().getBairro());
		comando.setString(4, f.getEndereco().getCidade());
		comando.setString(5, f.getEndereco().getEstado());
		comando.setString(6, f.getTipo_pessoa());
		comando.setString(7, f.getTelefone());
		comando.setString(8, f.getEmail());
		comando.setString(9, f.getFax());
		comando.setString(10, f.getPessoafisica().getCpf());
		comando.setString(11, f.getPessoafisica().getNome());
		comando.setString(12, f.getPessoafisica().getRg());
		comando.setString(12, f.getPessoafisica().getData_nasc());
		
		comando.executeUpdate();
	}
	
	//COMANDO PARA LISTAR OS DADOS DOS FORNECEDORES EM ASSOCIAÇÃO COM A CLASSE ENDEREÇO
	@Override
	public ArrayList<Fornecedor> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT f.codigo, f.tipo_pessoa, f.telefone, f.email, f.fax, ");
		sql.append("e.codigo, e.rua, e.numero, e.bairro, e.cidade, e.estado, ");
		sql.append("pf.cpf, pf.nome, pf.rg, pf.data_nasc ");
		sql.append("FROM Fornecedor f ");
		sql.append("INNER JOIN Endereco e ON e.codigo = f.Endereco_codigo ");
		sql.append("INNER JOIN Pessoa_Fisica pf ON pf.Fornecedor_codigo = f.codigo ORDER BY pf.nome ");
		
		Connection conexao = ConexaoFactory.conectar(); // COMANDO PARA CONECTAR COM O BANCO DE DADOS
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI À VARIÁVEL COMANDO
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fornecedor> itens = new ArrayList<Fornecedor>();
		
		while (resultado.next()) {
			
			Fornecedor f = new Fornecedor();
			Endereco e = new Endereco();
			e.setCodigo(resultado.getLong("e.codigo"));
			e.setRua(resultado.getString("e.rua"));
			e.setNumero(resultado.getString("e.numero"));
			e.setBairro(resultado.getString("e.bairro"));
			e.setCidade(resultado.getString("e.cidade"));
			e.setEstado(resultado.getString("e.estado"));
			f.setEndereco(e);
			
			
			f.setCodigo(resultado.getLong("f.codigo"));
			f.setTipo_pessoa(resultado.getString("f.tipo_pessoa"));
			f.setTelefone(resultado.getString("f.telefone"));
			f.setEmail(resultado.getString("f.email"));
			f.setFax(resultado.getString("f.fax"));
			
			Pessoa_Fisica pf = new Pessoa_Fisica();
			pf.setCpf(resultado.getString("pf.cpf"));
			pf.setNome(resultado.getString("pf.nome"));
			pf.setRg(resultado.getString("pf.rg"));
			pf.setData_nasc(resultado.getString("pf.data_nasc"));
			
			f.setPessoafisica(pf);
			
						
			
			itens.add(f);
		}
		
		return itens;
	}
	
	//COMANDO PARA EXCLUIR OS DADOS DOS FORNECEDORES EM ASSOCIAÇÃO COM A CLASSE ENDEREÇO
	@Override
	public void excluir(Fornecedor f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("START TRANSACTION; ");
			sql.append("DELETE pf FROM Pessoa_Fisica pf INNER JOIN Fornecedor f on pf.Fornecedor_codigo = f.codigo WHERE pf.Fornecedor_codigo = ?; ");
			sql.append("DELETE f FROM Fornecedor f INNER JOIN Pessoa_Fisica pf on pf.Fornecedor_codigo = f.codigo WHERE f.codigo = ?; ");
		sql.append("COMMIT; ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI À VARIÁVEL COMANDO
		
		comando.setLong(1, f.getCodigo());
		comando.setLong(2, f.getCodigo());
		
		comando.executeUpdate();
	}
	
	//MÉTODO PARA EDITAR OS DADOS DOS FORNECEDORES EM ASSOCIAÇÃO COM A CLASSE ENDEREÇO E PESSOA JURÍDICA
	@Override
	public void editar(Fornecedor f) throws SQLException {
		
		
		StringBuilder sql = new StringBuilder();
		sql.append("START TRANSACTION; ");
		
			sql.append("UPDATE Pessoa_Juridica pj ");
			sql.append("INNER JOIN Fornecedor f ON pj.Fornecedor_codigo = f.codigo ");
			sql.append("INNER JOIN Endereco e ON f.Endereco_codigo = e.codigo ");
			sql.append("SET e.rua = ?, e.numero = ?, e.bairro = ?, e.cidade = ?, e.estado, ");
			sql.append("f.tipo_pessoa = ?, f.telefone = ?, f.email = ?, f.fax = ?, ");
			sql.append("pf.cpf = ?, pf.nome = ?, pf.rg = ?, pf.data_nasc = ? ");
			sql.append("WHERE f.codigo = ?; ");

		sql.append("COMMIT; ");
		
		Connection conexao = ConexaoFactory.conectar(); // COMANDO PARA CONECTAR COM O BANCO DE DADOS
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI À VARIÁVEL COMANDO
		
		comando.setString(1, f.getEndereco().getRua());
		comando.setString(2, f.getEndereco().getNumero());
		comando.setString(3, f.getEndereco().getBairro());
		comando.setString(4, f.getEndereco().getCidade());
		comando.setString(5, f.getEndereco().getEstado());
		comando.setString(6, f.getTipo_pessoa());
		comando.setString(7, f.getTelefone());
		comando.setString(8, f.getEmail());
		comando.setString(9, f.getFax());
		comando.setString(10, f.getPessoafisica().getCpf());
		comando.setString(11, f.getPessoafisica().getNome());
		comando.setString(12, f.getPessoafisica().getRg());
		comando.setString(13, f.getPessoafisica().getData_nasc());
		comando.setLong(14, f.getCodigo());
		
		comando.executeUpdate();
	}
}
