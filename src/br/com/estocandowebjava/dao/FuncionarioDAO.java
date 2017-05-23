package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.Cargo;
import br.com.estocandowebjava.domain.Endereco;
import br.com.estocandowebjava.domain.Funcionario;
import br.com.estocandowebjava.domain.Setor;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class FuncionarioDAO {

	// DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvar(Funcionario f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Funcionario ");
		sql.append("(nome, cpf, rg, data_nasc, sexo, ctps, ");
		sql.append("data_admissao, pis, tipo_sang, Cargo_cod_cargo, login, ");
		sql.append("senha, Setor_cod_setor) ");
		sql.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getNome());
		comando.setString(2, f.getCpf());
		comando.setString(3, f.getRg());
		comando.setString(4, f.getData_nasc());
		comando.setString(5, f.getSexo());
		comando.setString(6, f.getCtps());
		comando.setString(7, f.getData_admissao());
		comando.setString(8, f.getPis());
		comando.setString(9, f.getTipo_sang());
		comando.setLong(10, f.getCargo().getCodigo());
		comando.setString(11, f.getLogin());
		comando.setString(12, f.getSenha());
		comando.setLong(13, f.getSetor().getCodigo());

		comando.executeUpdate();
	}
	
	//-----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA LISTAR OS DADOS DA TABELA
	public ArrayList<Funcionario> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * ");
		sql.append("FROM Funcionario f inner join cargo c ");
		sql.append("on c.cod_cargo = f.Cargo_cod_cargo ");
		sql.append("inner join setor s on s.cod_setor = f.Setor_cod_setor order by f.nome ");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Funcionario> itens = new ArrayList<Funcionario>();
		
		while(resultado.next()) {
			Funcionario f = new Funcionario();
			f.setMatricula(resultado.getLong("f.matricula"));
			f.setNome(resultado.getString("f.nome"));
			f.setCpf(resultado.getString("f.cpf"));
			f.setRg(resultado.getString("f.rg"));
			f.setData_nasc(resultado.getString("f.data_nasc"));
			f.setSexo(resultado.getString("f.sexo"));
			f.setCtps(resultado.getString("f.ctps"));
			f.setData_admissao(resultado.getString("f.data_admissao"));
			f.setPis(resultado.getString("f.pis"));
			f.setTipo_sang(resultado.getString("f.tipo_sang"));
			f.setLogin(resultado.getString("f.login"));
			f.setSenha(resultado.getString("f.senha"));
			
			Cargo c = new Cargo();
			c.setDescricao(resultado.getString("c.descricao"));
			c.setSalario(resultado.getDouble("c.salario"));
			f.setCargo(c);
			
			Setor s = new Setor();
			s.setDescricao(resultado.getString("s.descricao"));
			f.setSetor(s);
			
			itens.add(f);
		}
		
		return itens;

	}
	
	//----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA LISTAR OS DADOS DA TABELA
		public ArrayList<Funcionario> listarDistincao() throws SQLException {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT DISTINCT nome ");
			sql.append("FROM Funcionario f inner join cargo c ");
			sql.append("on c.cod_cargo = f.Cargo_cod_cargo ");
			sql.append("inner join setor s on s.cod_setor = f.Setor_cod_setor order by f.nome");
			
			// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
			Connection conexao = ConexaoFactory.conectar();

			// COMANDO DE PREPARAÇÃO
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			
			ResultSet resultado = comando.executeQuery();
			
			ArrayList<Funcionario> itens = new ArrayList<Funcionario>();
			
			while(resultado.next()) {
				Funcionario f = new Funcionario();
				f.setMatricula(resultado.getLong("f.matricula"));
				f.setNome(resultado.getString("f.nome"));
				f.setCpf(resultado.getString("f.cpf"));
				f.setRg(resultado.getString("f.rg"));
				f.setData_nasc(resultado.getString("f.data_nasc"));
				f.setSexo(resultado.getString("f.sexo"));
				f.setCtps(resultado.getString("f.ctps"));//ctps
				f.setData_admissao(resultado.getString("f.data_admissao"));//data_admissao
				f.setPis(resultado.getString("f.pis"));//pis
				f.setTipo_sang(resultado.getString("f.tipo_sang"));//tipo_sang
				f.setLogin(resultado.getString("f.login"));//login
				f.setSenha(resultado.getString("f.senha"));//senha
				
				Cargo c = new Cargo();
				c.setDescricao(resultado.getString("c.descricao"));
				c.setSalario(resultado.getDouble("c.salario"));
				f.setCargo(c);
				
				Setor s = new Setor();
				s.setDescricao(resultado.getString("s.descricao"));
				f.setSetor(s);
				
				itens.add(f);
			}
			
			return itens;

		}
	
	//----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA EXCLUIR DADOS DA TABELA
	public void excluir(Funcionario f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM Funcionario WHERE matricula = ?");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getMatricula());
		
		comando.executeUpdate();
	}
	
	//----------------------------------------------------------------
	
	//DEFINIÇÃO DO COMANDO PARA EDITAR DADOS DA TABELA
	public void editar(Funcionario f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE Funcionario SET ");
		sql.append("nome = ?, cpf = ?, rg = ?, data_nasc = ?, sexo = ?, ctps = ?, ");
		sql.append("data_admissao = ?, pis = ?, tipo_sang = ?, Cargo_cod_cargo = ?, login = ?, ");
		sql.append("senha = ?, Setor_cod_setor = ? ");
		sql.append("WHERE matricula = ?");
		
		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getNome());
		comando.setString(2, f.getCpf());
		comando.setString(3, f.getRg());
		comando.setString(4, f.getData_nasc());
		comando.setString(5, f.getSexo());
		comando.setString(6, f.getCtps());
		comando.setString(7, f.getData_admissao());
		comando.setString(8, f.getPis());
		comando.setString(9, f.getTipo_sang());
		comando.setLong(10, f.getCargo().getCodigo());
		comando.setString(11, f.getLogin());
		comando.setString(12, f.getSenha());
		comando.setLong(13, f.getSetor().getCodigo());
		comando.setLong(14, f.getMatricula());

		comando.executeUpdate();
	}

}
