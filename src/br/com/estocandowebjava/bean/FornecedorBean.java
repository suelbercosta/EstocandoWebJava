package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.EnderecoDAO;
import br.com.estocandowebjava.dao.FornecedorDAO;
import br.com.estocandowebjava.domain.Endereco;
import br.com.estocandowebjava.domain.Fornecedor;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBFornecedor")
@ViewScoped
public class FornecedorBean implements InterfaceBean {
	private Fornecedor fornecedor;
	private ArrayList<Endereco> comboEndereco;

	private ArrayList<Fornecedor> itens;
	private ArrayList<Fornecedor> itensFiltrados;

	public ArrayList<Fornecedor> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedor> itens) {
		this.itens = itens;
	}

	public ArrayList<Fornecedor> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fornecedor> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public ArrayList<Endereco> getComboEndereco() {
		return comboEndereco;
	}

	public void setComboEndereco(ArrayList<Endereco> comboEndereco) {
		this.comboEndereco = comboEndereco;
	}

	@Override
	public void carregarListagem() {
		try {
			FornecedorDAO dao = new FornecedorDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// COMANDO PARA PREPARAR NOVO FORNECEDORES
	@Override
	public void prepararNovo() {
		try {
			fornecedor = new Fornecedor();

			EnderecoDAO edao = new EnderecoDAO();

			comboEndereco = edao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	@Override
	public void novo() {
		try {
			FornecedorDAO fdao = new FornecedorDAO();
			fdao.salvar(fornecedor);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados salvos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	// COMANDO PARA EXCLUIR UM FORNECEDORES
	@Override
	public void excluir() {
		try {
			FornecedorDAO fdao = new FornecedorDAO();
			
			fdao.excluir(fornecedor);
			
			itens = fdao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Dados excluidos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	// COMANDO PARA PREPARAR EDITAR FORNECEDORES
	@Override
	public void prepararEditar() {
		try {
			EnderecoDAO edao = new EnderecoDAO();

			comboEndereco = edao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
	@Override
	public void editar() {
		try {
			FornecedorDAO fdao = new FornecedorDAO();
			
			fdao.editar(fornecedor);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados editados com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

}
