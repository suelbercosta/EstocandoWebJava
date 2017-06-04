package br.com.estocandowebjava.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.estocandowebjava.domain.Fornecedor;

public interface FornecedorDAO {
	//DEFINIÇÃO DO COMANDO SQL PARA SALVAR OS DADOS NO BANCO DE DADOS
	public void salvar(Fornecedor f) throws SQLException;
	
	//COMANDO PARA LISTAR OS DADOS DOS FORNECEDORES EM ASSOCIAÇÃO COM A CLASSE ENDEREÇO
	public ArrayList<Fornecedor> listar() throws SQLException;
	
	//COMANDO PARA EXCLUIR OS DADOS DOS FORNECEDORES EM ASSOCIAÇÃO COM A CLASSE ENDEREÇO
	public void excluir(Fornecedor f) throws SQLException;
	
	//MÉTODO PARA EDITAR OS DADOS DOS FORNECEDORES EM ASSOCIAÇÃO COM A CLASSE ENDEREÇO E PESSOA JURÍDICA
	public void editar(Fornecedor f) throws SQLException;
}
