package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.FornecedoresDAO;
import br.com.estocandowebjava.util.JSFUtil;
import br.com.estocandowebjava.domain.Fornecedor;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {
	
	public ArrayList<Fornecedor> comboFornecedores;
	private ArrayList<Fornecedor> itensFiltrados;
	
	public void carregarListagem() {
		try {
			FornecedoresDAO dao = new FornecedoresDAO();
			ArrayList<Fornecedor> itens = dao.fornecedores();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
