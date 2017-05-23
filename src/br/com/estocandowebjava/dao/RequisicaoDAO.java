package br.com.estocandowebjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.Funcionario;
import br.com.estocandowebjava.domain.Requisicao;
import br.com.estocandowebjava.factoty.ConexaoFactory;

public class RequisicaoDAO {

	// DEFINI��O DO COMANDO SQL PARA SALVAR OS DADOS
	public void salvar(Requisicao r) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into Requisicao (data, almoxarife, requisitante_codigo) values (?, ?, ?) ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, r.getData());
		comando.setLong(2, r.getAlmoxarife().getMatricula());
		comando.setLong(3, r.getRequisitante().getMatricula());

		comando.executeUpdate();
	}
	
	//----------------------------------------------------------------
	
	//CRIA��O DE ARRAYLIST PARA LISTAR TODOS OS CARGOS
	public ArrayList<Requisicao> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select r.codigo, r.data, r.almoxarife, ");
		sql.append("r.requisitante_codigo, f1.nome, f2.nome ");
		sql.append("from Requisicao r ");
		sql.append("inner join Funcionario as f1 on f1.matricula = r.almoxarife ");
		sql.append("inner join Funcionario as f2 on f2.matricula = r.requisitante_codigo ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		//CRIA��O DO ARRAYLIST DO TIPO CARGO
		ArrayList<Requisicao> lista = new ArrayList<Requisicao>();

		//ESTRUTURA DE REPETI��O PARA COLETAR TODOS OS DADOS DA CONSULTA
		while (resultado.next()) {
			Requisicao r = new Requisicao();
			r.setCodigo(resultado.getLong("r.codigo"));
			r.setData(resultado.getString("r.data"));
			
			Funcionario f1 = new Funcionario();
			f1.setNome(resultado.getString("f1.nome"));
			r.setAlmoxarife(f1);
			
			Funcionario f2 = new Funcionario();
			f2.setNome(resultado.getString("f2.nome"));
			r.setRequisitante(f2);

			lista.add(r);
		}

		return lista;
	}

	//----------------------------------------------------------------

	// DEFINI��O DO COMANDO SQL PARA EXCLUIR DADOS
	public void excluir(Requisicao r) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from Requisicao where codigo = ? ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, r.getCodigo());

		comando.executeUpdate();

	}
	
	//----------------------------------------------------------------

	// DEFINI��O DO COMANDO SQL PARA EDITAR OS DADOS
	public void editar(Requisicao r) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("update Requisicao set data = ?, almoxarife = ?, requisitante_codigo = ? where codigo = ? ");

		// CRIA��O DA CONEX�O COM O BANCO DE DADOS
		Connection conexao = ConexaoFactory.conectar();

		// COMANDO DE PREPARA��O
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, r.getData());
		comando.setLong(2, r.getAlmoxarife().getMatricula());
		comando.setLong(3, r.getRequisitante().getMatricula());
		comando.setLong(4, r.getCodigo());

		comando.executeUpdate();

	}

}
