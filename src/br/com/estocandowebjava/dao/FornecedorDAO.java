package br.com.estocandowebjava.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.Fornecedor;

public interface FornecedorDAO {
	//DEFINI��O DO COMANDO SQL PARA SALVAR OS DADOS NO BANCO DE DADOS
	public void salvar(Fornecedor f) throws SQLException;
	
	//COMANDO PARA LISTAR OS DADOS DOS FORNECEDORES EM ASSOCIA��O COM A CLASSE ENDERE�O
	public ArrayList<Fornecedor> listar() throws SQLException;
	
	//COMANDO PARA EXCLUIR OS DADOS DOS FORNECEDORES EM ASSOCIA��O COM A CLASSE ENDERE�O
	public void excluir(Fornecedor f) throws SQLException;
	
	//M�TODO PARA EDITAR OS DADOS DOS FORNECEDORES EM ASSOCIA��O COM A CLASSE ENDERE�O E PESSOA JUR�DICA
	public void editar(Fornecedor f) throws SQLException;
}
