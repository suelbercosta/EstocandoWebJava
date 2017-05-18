package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.FornecedorDAO;
import br.com.estocandowebjava.domain.Fornecedor;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBFornecedor")
@ViewScoped
public class FornecedorBean {
	private ArrayList<Fornecedor> itens;

	public ArrayList<Fornecedor> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedor> itens) {
		this.itens = itens;
	}

	public void carregarListagem() {
		try {
			FornecedorDAO dao = new FornecedorDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace(); // comando para imprimir a pilha de execução
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
