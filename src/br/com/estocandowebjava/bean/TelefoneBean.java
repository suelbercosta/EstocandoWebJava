package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.FuncionarioDAO;
import br.com.estocandowebjava.dao.TelefoneDAO;
import br.com.estocandowebjava.domain.Funcionario;
import br.com.estocandowebjava.domain.Telefone;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBTelefone")
@ViewScoped
public class TelefoneBean implements InterfaceBean {
	private Telefone Telefone = new Telefone();
	private ArrayList<Funcionario> comboFuncionario;

	private ArrayList<Telefone> itens;
	private ArrayList<Telefone> itensFiltrados;

	public ArrayList<Telefone> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Telefone> itens) {
		this.itens = itens;
	}

	public ArrayList<Telefone> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Telefone> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Telefone getTelefone() {
		return Telefone;
	}

	public void setTelefone(Telefone Telefone) {
		this.Telefone = Telefone;
	}
	
	public ArrayList<Funcionario> getComboFuncionario() {
		return comboFuncionario;
	}

	public void setComboFuncionario(ArrayList<Funcionario> comboFuncionario) {
		this.comboFuncionario = comboFuncionario;
	}
	

	@Override
	// MÉTODO PARA LISTAR TODOS OS TELEFONES
	public void carregarListagem() {
		try {
			TelefoneDAO dao = new TelefoneDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// MÉTODO PARA PREPARAR NOVO TELEFONES
	@Override
	public void prepararNovo() {
		try {
			Telefone = new Telefone();

			FuncionarioDAO fdao = new FuncionarioDAO();

			comboFuncionario = fdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	@Override
	public void novo() {
		try {
			TelefoneDAO tdao = new TelefoneDAO();
			tdao.salvar(Telefone);

			itens = tdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados salvos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	// MÉTODO PARA EXCLUIR UM TELEFONES
	@Override
	public void excluir() {
		try {
			TelefoneDAO tdao = new TelefoneDAO();
			
			tdao.excluir(Telefone);
			
			itens = tdao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Dados excluidos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	// MÉTODO PARA PREPARAR EDITAR TELEFONES
	@Override
	public void prepararEditar() {
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();

			comboFuncionario = fdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
	@Override
	// MÉTODO PARA EDITAR TELEFONES
	public void editar() {
		try {
			TelefoneDAO fdao = new TelefoneDAO();
			
			fdao.editar(Telefone);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados editados com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

}
