package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.fabric.FabricCommunicationException;

import br.com.estocandowebjava.domain.Funcionario;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class FuncionarioDAO {

	// DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvarFuncionario(Funcionario f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Funcionario (nome, cpf, rg, data_nasc, sexo, ctps, ");
		sql.append("data_admissao, pis, tipo_sang, Cargo_cod_cargo, login, ");
		sql.append("senha, Endereco_codigo, Setor_cod_setor) ");
		sql.append("values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getNome());
		comando.setString(2, f.getCpf());
		comando.setString(3, f.getRg());
		comando.setString(4, f.getData_nasc());
		comando.setString(5, f.getSexo());
		comando.setLong(6, f.getCtps());
		comando.setString(14, f.getData_admissao());
		comando.setLong(7, f.getPis());
		comando.setString(8, f.getTipo_sang());
		comando.setLong(9, f.getCargo().getCodigo());
		comando.setString(10, f.getLogin());
		comando.setString(11, f.getSenha());
		comando.setLong(12, f.getEndereco().getCodigo());
		comando.setLong(13, f.getSetor().getCodigo());

		comando.executeUpdate();
	}

	// DEFINIÇÃO DO COMANDO SQL PARA EXCLUIR DADOS
	public void excluirFuncionario(Funcionario f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from funcionario where matricula = ? ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getMatricula());

		comando.executeUpdate();

	}

	// DEFINIÇÃO DO COMANDO SQL PARA EDITAR OS DADOS
	public void editarFuncionario(Funcionario f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update Funcionario set nome = ?, cpf = ?, rg = ?, data_nasc = ?, ");
		sql.append("sexo = ?, ctps = ?, data_admissao = ?, pis = ?, tipo_sang = ?, cargo = ?, ");
		sql.append("login = ?,  senha= ?, endereco = ?, setor = ? ");
		sql.append("where matricula = ? ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getNome());
		comando.setString(2, f.getCpf());
		comando.setString(3, f.getRg());
		comando.setString(4, f.getData_nasc());
		comando.setString(5, f.getSexo());
		comando.setLong(6, f.getCtps());
		comando.setString(14, f.getData_admissao());
		comando.setLong(7, f.getPis());
		comando.setString(8, f.getTipo_sang());
		comando.setLong(9, f.getCargo().getCodigo());
		comando.setString(10, f.getLogin());
		comando.setString(11, f.getSenha());
		comando.setLong(12, f.getEndereco().getCodigo());
		comando.setLong(13, f.getSetor().getCodigo());
		comando.setLong(14, f.getMatricula());

		comando.executeUpdate();

	}

	// DEFINIÇÃO DO COMANDO SQL PARA EDITAR OS DADOS
	public Funcionario buscarPorCodigo(Funcionario f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select matricula, nome from Funcionario where matricula = ? ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getMatricula());

		ResultSet resultado = comando.executeQuery();

		Funcionario retorno = null;

		// CONDIÇÃO QUE DIZ SE A CONSULTA RETORNOU MAIS DE UM VALOR
		if (resultado.next()) {
			retorno = new Funcionario();
			retorno.setMatricula(resultado.getLong("matricula"));
			retorno.setNome(resultado.getString("nome"));
		}

		return retorno;
	}

	//CRIAÇÃO DE ARRAYLIST PARA LISTAR TODOS OS CARGOS
	public ArrayList<Funcionario> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from Funcionario ORDER BY nome asc ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARAÇÃO
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		//CRIAÇÃO DO ARRAYLIST DO TIPO CARGO
		ArrayList<Funcionario> lista = new ArrayList<Funcionario>();

		//ESTRUTURA DE REPETIÇÃO PARA COLETAR TODOS OS DADOS DA CONSULTA
		while (resultado.next()) {
			Funcionario f1 = new Funcionario();
			f1.setMatricula(resultado.getLong("matricula"));
			f1.setNome(resultado.getString("nome"));
			f1.setCpf(resultado.getString("cpf"));
			f1.setRg(resultado.getString("rg"));
			f1.setData_nasc(resultado.getString("data_nasc"));
			f1.setSexo(resultado.getString("sexo"));
			f1.setCtps(resultado.getLong("ctps"));
			f1.setData_admissao(resultado.getString("data_admissao"));
			f1.setPis(resultado.getLong("pis"));
			f1.setTipo_sang(resultado.getString("tipo_sang"));
			//f1.setCargo(resultado.getLong("cargo"));
			f1.setLogin(resultado.getString("login"));
			f1.setSenha(resultado.getString("senha"));
			//f1.setEndereco(resultado.getLong("endereco"));
			//f1.setSetor(resultado.getLong("setor"));

			lista.add(f1);
		}

		return lista;
	}
	
	//CRIAÇÃO DE ARRAYLIST PARA LISTAR OS DADOS PESQUISADOS POR DESCRIÇÃO
	public ArrayList<Funcionario> buscarPorDescricao(Funcionario f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT matricula, nome FROM Funcionario WHERE nome LIKE ? ");
		sql.append("ORDER BY nome ASC ");

		// CRIAÇÃO DA CONEXÃO COM O BANCO DE DADOS
				Connection conexao = ConexaoFactory.conectar();

				// COMANDO DE PREPARAÇÃO
				PreparedStatement comando = conexao.prepareStatement(sql.toString());
				comando.setString(1, "%" + f.getNome() + "%");
				
				ResultSet resultado = comando.executeQuery();
				
				//CRIAÇÃO DO ARRAYLIST DO TIPO CARGO
				ArrayList<Funcionario> lista = new ArrayList<Funcionario>();

				//ESTRUTURA DE REPETIÇÃO PARA COLETAR TODOS OS DADOS DA CONSULTA
				while (resultado.next()) {
					Funcionario item = new Funcionario();
					item.setMatricula(resultado.getLong("matricula"));
					item.setNome(resultado.getString("nome"));

					lista.add(item);
				}

				return lista;
				
	}

	// CRIAÇÃO DO MÉTODO MAIN
	public static void main(String[] args) {
		/*
		 * CÓDIGO INSERÇÃO DOS DADOS NA TABELA Funcionario c1 = new Funcionario();
		 * c1.setDescricao("Gestor de Transportes"); c1.setSalario(6000.00);
		 * c1.setPermissao(0L);
		 * 
		 * Funcionario c2 = new Funcionario(); c2.setDescricao("Analista de Patrimônio");
		 * c2.setSalario(1730.00); c2.setPermissao(0L);
		 * 
		 * FuncionarioDAO cdao = new FuncionarioDAO(); // TRATAMENTO DE ERRO DA PERSISTÊNCIA
		 * NO BANCO DE DADOS try { cdao.salvarFuncionario(c1); cdao.salvarFuncionario(c2);
		 * System.out.println("O(s) funcionario(a) foi(ram) salvo(s) com sucesso."); }
		 * catch (SQLException e) {
		 * System.out.println("Ocorreu um erro ao tentar salvar os dados.");
		 * e.printStackTrace(); }
		 */

		/*
		 * CÓDIGO INSERÇÃO DOS DADOS NA TABELA Funcionario c1 = new Funcionario();
		 * c1.setCodigo(3L);
		 * 
		 * Funcionario c2 = new Funcionario(); c2.setCodigo(7L);
		 * 
		 * FuncionarioDAO cdao = new FuncionarioDAO();
		 * 
		 * // TRATAMENTO DE ERRO DE EXCLUSÃO NO BANCO DE DADOS try {
		 * cdao.excluirFuncionario(c1); cdao.excluirFuncionario(c2);
		 * System.out.println("Os funcionarios foram excluidos com sucesso."); } catch
		 * (SQLException e) {
		 * System.out.println("Ocorreu um erro ao tentar excluir o(s) funcionario(s)."
		 * ); e.printStackTrace(); }
		 */

		/*
		 * CÓDIGO ALTERAÇÃO DOS DADOS NA TABELA Funcionario c1 = new Funcionario();
		 * c1.setCodigo(2L); c1.setDescricao("Analista de Expedição");
		 * 
		 * FuncionarioDAO cdao = new FuncionarioDAO();
		 * 
		 * // TRATAMENTO DE ERRO DA ALTERAÇÃO NO BANCO DE DADOS try {
		 * cdao.editarFuncionario(c1);
		 * System.out.println("O funcionario foi editado com sucesso."); } catch
		 * (SQLException e) {
		 * System.out.println("Ocorreu um erro na alteração do funcionario.");
		 * e.printStackTrace(); }
		 */

		/*
		// CÓDIGO PARA CONSULTA NO BANCO DE DADOS
		Funcionario c1 = new Funcionario();
		c1.setCodigo(1L);

		Funcionario c2 = new Funcionario();
		c2.setCodigo(9L);

		FuncionarioDAO cdao = new FuncionarioDAO();

		// TRATAMENTO DE ERRO DA CONSULTA NO BANCO DE DADOS
		try {
			Funcionario f3 = cdao.buscarPorCodigo(c1);
			Funcionario f4 = cdao.buscarPorCodigo(c2);
			System.out.println("Resultado 1: " + f3);
			System.out.println("Resultado 2: " + f4);
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar perquisar um dos funcionarios.");
			e.printStackTrace();
		}*/
		
		
		
		// TRATAMENTO DE ERRO E LISTAGEM DA CONSULTA NO BANCO DE DADOS POR CÓDIGO
		FuncionarioDAO fdao = new FuncionarioDAO();
		try {
			ArrayList<Funcionario> lista = fdao.listar();
			
			for(Funcionario f : lista){
				System.out.println("Resultado: " + f);
			}
		}catch(SQLException ex) {
			System.out.println("Ocorreu um erro ao tentar listar os funcionarios!");
			ex.printStackTrace();
		} 
		
		// TRATAMENTO DE ERRO E LISTAGEM DA CONSULTA NO BANCO DE DADOS POR DESCRIÇÃO
		/*Funcionario c1 = new Funcionario();
		c1.setDescricao("de");
		
		FuncionarioDAO cdao = new FuncionarioDAO();
		
		try {
			ArrayList<Funcionario> lista = cdao.buscarPorDescricao(c1);
			
			for(Funcionario c : lista){
				System.out.println("Resultado: " + c);
			}
		}catch(SQLException e) {
			System.out.println("Ocorreu um erro ao tentar perquisar um funcionario.");
			e.printStackTrace();
		}*/

	}
}
