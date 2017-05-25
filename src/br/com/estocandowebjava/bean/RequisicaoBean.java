package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.FuncionarioDAO;
import br.com.estocandowebjava.dao.RequisicaoDAO;
import br.com.estocandowebjava.domain.Funcionario;
import br.com.estocandowebjava.domain.Requisicao;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBRequisicao")
@ViewScoped
public class RequisicaoBean implements InterfaceBean {
	private Requisicao requisicao;
	private ArrayList<Funcionario> comboAlmoxarife;
	private ArrayList<Funcionario> comboRequisitante;
	
	private ArrayList<Requisicao> itens;
	private ArrayList<Requisicao> itensFiltrados;



	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}

	public ArrayList<Funcionario> getComboAlmoxarife() {
		return comboAlmoxarife;
	}

	public void setComboAlmoxarife(ArrayList<Funcionario> comboAlmoxarife) {
		this.comboAlmoxarife = comboAlmoxarife;
	}

	public ArrayList<Funcionario> getComboRequisitante() {
		return comboRequisitante;
	}

	public void setComboRequisitante(ArrayList<Funcionario> comboRequisitante) {
		this.comboRequisitante = comboRequisitante;
	}

	public ArrayList<Requisicao> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Requisicao> itens) {
		this.itens = itens;
	}

	public ArrayList<Requisicao> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Requisicao> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@Override
	public void carregarListagem() {
		try {
			RequisicaoDAO dao = new RequisicaoDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// COMANDO PARA PREPARAR NOVA REQUISIÇÃO
	@Override
	public void prepararNovo() {
		try {
			requisicao = new Requisicao();

			FuncionarioDAO fdao = new FuncionarioDAO();

			comboAlmoxarife = fdao.listar();
			comboRequisitante = fdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	@Override
	public void novo() {
		try {
			RequisicaoDAO rdao = new RequisicaoDAO();
			rdao.salvar(requisicao);

			itens = rdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados salvos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	// COMANDO PARA EXCLUIR UMA REQUISIÇÃO
	@Override
	public void excluir() {
		try {
			RequisicaoDAO rdao = new RequisicaoDAO();
			
			rdao.excluir(requisicao);
			
			itens = rdao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Dados excluidos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	// COMANDO PARA PREPARAR EDITAR UMA REQUISIÇÃO
	@Override
	public void prepararEditar() {
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();

			comboAlmoxarife = fdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

		try {
			FuncionarioDAO fdao = new FuncionarioDAO();

			comboRequisitante = fdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}
	
	@Override
	public void editar() {
		try {
			RequisicaoDAO rdao = new RequisicaoDAO();
			
			rdao.editar(requisicao);

			itens = rdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados editados com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}