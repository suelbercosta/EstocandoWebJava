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

public class PessoaFisicaDAO extends FornecedorDAO{
	//DEFINI��O DO COMANDO SQL PARA SALVAR OS DADOS NO BANCO DE DADOS
	@Override
	public void salvar(Fornecedor f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("START TRANSACTION; ");
			StringBuilder sql1 = new StringBuilder();
			sql1.append("INSERT INTO Endereco (rua, numero, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?); ");
			StringBuilder sql2 = new StringBuilder();
			sql2.append("SET @cod_end = LAST_INSERT_ID();");
			StringBuilder sql3 = new StringBuilder();
			sql3.append("INSERT INTO Fornecedor (tipo_pessoa, telefone, email, fax, Endereco_codigo) VALUES (?, ?, ?, ?, @cod_end); ");
			StringBuilder sql4 = new StringBuilder();
			sql4.append("SET @cod_forn = LAST_INSERT_ID();");
			StringBuilder sql5 = new StringBuilder();
			sql5.append("INSERT INTO Pessoa_Fisica (cpf, nome, rg, data_nasc, Fornecedor_codigo) VALUES (?, ?, ?, ?, @cod_forn); ");
		StringBuilder sql6 = new StringBuilder();
		sql6.append("COMMIT; ");
		
		Connection conexao = ConexaoFactory.conectar(); // COMANDO PARA CONECTAR COM O BANCO DE DADOS
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI � VARI�VEL COMANDO
		PreparedStatement comando1 = conexao.prepareStatement(sql1.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI � VARI�VEL COMANDO
		PreparedStatement comando2 = conexao.prepareStatement(sql2.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI � VARI�VEL COMANDO
		PreparedStatement comando3 = conexao.prepareStatement(sql3.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI � VARI�VEL COMANDO
		PreparedStatement comando4 = conexao.prepareStatement(sql4.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI � VARI�VEL COMANDO
		PreparedStatement comando5 = conexao.prepareStatement(sql5.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI � VARI�VEL COMANDO
		PreparedStatement comando6 = conexao.prepareStatement(sql6.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI � VARI�VEL COMANDO
		
		comando1.setString(1, f.getEndereco().getRua());
		comando1.setString(2, f.getEndereco().getNumero());
		comando1.setString(3, f.getEndereco().getBairro());
		comando1.setString(4, f.getEndereco().getCidade());
		comando1.setString(5, f.getEndereco().getEstado());
		
		comando3.setString(1, f.getTipo_pessoa());
		comando3.setString(2, f.getTelefone());
		comando3.setString(3, f.getEmail());
		comando3.setString(4, f.getFax());
		
		comando5.setString(1, f.getPessoafisica().getCpf());
		comando5.setString(2, f.getPessoafisica().getNome());
		comando5.setString(3, f.getPessoafisica().getRg());
		comando5.setString(4, f.getPessoafisica().getData_nasc());
		
		comando.executeUpdate();
		comando1.executeUpdate();
		comando2.executeUpdate();
		comando3.executeUpdate();
		comando4.executeUpdate();
		comando5.executeUpdate();
		comando6.executeUpdate();
	}
	
	//COMANDO PARA LISTAR OS DADOS DOS FORNECEDORES EM ASSOCIA��O COM A CLASSE ENDERE�O
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
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI � VARI�VEL COMANDO
		
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
	
	//COMANDO PARA EXCLUIR OS DADOS DOS FORNECEDORES EM ASSOCIA��O COM A CLASSE ENDERE�O
	@Override
	public void excluir(Fornecedor f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE pf FROM Pessoa_Fisica pf INNER JOIN Fornecedor f on pf.Fornecedor_codigo = f.codigo WHERE pf.Fornecedor_codigo = ?; ");
		StringBuilder sql1 = new StringBuilder();
		sql1.append("DELETE FROM Fornecedor WHERE codigo = ?; ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI � VARI�VEL COMANDO
		PreparedStatement comando1 = conexao.prepareStatement(sql1.toString());
		
		comando.setLong(1, f.getCodigo());
		comando1.setLong(1, f.getCodigo());
		
		comando.executeUpdate();
		comando1.executeUpdate();
	}
	
	//M�TODO PARA EDITAR OS DADOS DOS FORNECEDORES EM ASSOCIA��O COM A CLASSE ENDERE�O E PESSOA JUR�DICA
	@Override
	public void editar(Fornecedor f) throws SQLException {
		
		StringBuilder sql = new StringBuilder();		
			sql.append("UPDATE Endereco e ");
			sql.append("INNER JOIN Fornecedor f ON e.codigo = f.Endereco_codigo ");
			sql.append("SET e.rua = ?, e.numero = ?, e.bairro = ?, e.cidade = ?, e.estado = ? ");
			sql.append("WHERE f.codigo = ?; ");
		
		StringBuilder sql1 = new StringBuilder();
			sql1.append("UPDATE Fornecedor f ");
			sql1.append("SET f.tipo_pessoa = ?, f.telefone = ?, f.email = ?, f.fax = ? ");
			sql1.append("WHERE f.codigo = ?; ");
			
		StringBuilder sql2 = new StringBuilder();
			sql2.append("UPDATE Pessoa_Fisica pf ");
			sql2.append("INNER JOIN Fornecedor f ON f.codigo = pf.Fornecedor_codigo ");
			sql2.append("SET pf.cpf = ?, pf.nome = ?, pf.rg = ?, pf.data_nasc = ? ");
			sql2.append("WHERE f.codigo = ?; ");
		
		Connection conexao = ConexaoFactory.conectar(); // COMANDO PARA CONECTAR COM O BANCO DE DADOS
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); // CONVERTE O STRINGBUILDER PARA STRING E ATRIBUI � VARI�VEL COMANDO
		PreparedStatement comando1 = conexao.prepareStatement(sql1.toString()); 
		PreparedStatement comando2 = conexao.prepareStatement(sql2.toString()); 
		
		comando.setString(1, f.getEndereco().getRua());
		comando.setString(2, f.getEndereco().getNumero());
		comando.setString(3, f.getEndereco().getBairro());
		comando.setString(4, f.getEndereco().getCidade());
		comando.setString(5, f.getEndereco().getEstado());
		comando.setLong(6, f.getCodigo());
		
		comando1.setString(1, f.getTipo_pessoa());
		comando1.setString(2, f.getTelefone());
		comando1.setString(3, f.getEmail());
		comando1.setString(4, f.getFax());
		comando1.setLong(5, f.getCodigo());
		
		comando2.setString(1, f.getPessoafisica().getCpf());
		comando2.setString(2, f.getPessoafisica().getNome());
		comando2.setString(3, f.getPessoafisica().getRg());
		comando2.setString(4, f.getPessoafisica().getData_nasc());
		comando2.setLong(5, f.getCodigo());
		
		comando.executeUpdate();
		comando1.executeUpdate();
		comando2.executeUpdate();
	}
}
