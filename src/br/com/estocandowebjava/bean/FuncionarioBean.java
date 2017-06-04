package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.CargoDAO;
import br.com.estocandowebjava.dao.FuncionarioDAO;
import br.com.estocandowebjava.dao.SetorDAO;
import br.com.estocandowebjava.domain.Cargo;
import br.com.estocandowebjava.domain.Endereco;
import br.com.estocandowebjava.domain.Funcionario;
import br.com.estocandowebjava.domain.Setor;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBFuncionario")
@ViewScoped
// IMPLEMENTA��O DA INTERFACE JAVA
public class FuncionarioBean implements InterfaceBean {
	private Funcionario funcionario = new Funcionario();
	private ArrayList<Cargo> comboCargo; // CRIA��O DO ARRAYLIST PARA OS CARGOS
	private ArrayList<Setor> comboSetor; // CRIA��O DO ARRAYLIST PARA OS SETORES
	private ArrayList<Endereco> comboEndereco; // CRIA��O DO ARRAYLIST PARA OS
												// ENDERE�OS

	private ArrayList<Funcionario> itens;
	private ArrayList<Funcionario> itensFiltrados;

	public ArrayList<Funcionario> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Funcionario> itens) {
		this.itens = itens;
	}

	public ArrayList<Funcionario> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Funcionario> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public ArrayList<Cargo> getComboCargo() {
		return comboCargo;
	}

	public void setComboCargo(ArrayList<Cargo> comboCargo) {
		this.comboCargo = comboCargo;
	}

	public ArrayList<Setor> getComboSetor() {
		return comboSetor;
	}

	public void setComboSetor(ArrayList<Setor> comboSetor) {
		this.comboSetor = comboSetor;
	}

	public ArrayList<Endereco> getComboEndereco() {
		return comboEndereco;
	}

	public void setComboEndereco(ArrayList<Endereco> comboEndereco) {
		this.comboEndereco = comboEndereco;
	}

	@Override // SOBRE-ESCRITA DO M�TODO CARREGARLISTAGEM() DA INTERFACEBEAN
	// M�TODO PARA LISTAGEM DOS FUNCION�RIOS
	public void carregarListagem() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	@Override // SOBRE-ESCRITA DO M�TODO PREPARARNOVO() DA INTERFACEBEAN
	// M�TODO PARA PREPARAR NOVO FUNCION�RIOS
	public void prepararNovo() {
		try {
			funcionario = new Funcionario();

			CargoDAO cdao = new CargoDAO();

			comboCargo = cdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

		try {
			funcionario = new Funcionario();

			SetorDAO sdao = new SetorDAO();

			comboSetor = sdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	@Override // SOBRE-ESCRITA DO M�TODO NOVO() DA INTERFACEBEAN
	// M�TODO PARA INCLUIR UM NOVO FUNCION�RIO
	public void novo() {
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();
			fdao.salvar(funcionario);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados salvos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	@Override // SOBRE-ESCRITA DO M�TODO EXCLUIR() DA INTERFACEBEAN
	// M�TODO PARA EXCLUIR UM FUNCION�RIOS
	public void excluir() {
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();

			fdao.excluir(funcionario);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados excluidos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	@Override // SOBRE-ESCRITA DO M�TODO PREPARAREDITAR() DA INTERFACEBEAN
	// M�TODO PARA PREPARAR A EDI��O DE FUNCION�RIOS
	public void prepararEditar() {
		try {
			CargoDAO cdao = new CargoDAO();

			comboCargo = cdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

		try {
			SetorDAO sdao = new SetorDAO();

			comboSetor = sdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	@Override // SOBRE-ESCRITA DO M�TODO EDITAR() DA INTERFACEBEAN
	// M�TODO PARA EDITAR UM FUNCION�RIO
	public void editar() {
		try {
			FuncionarioDAO fdao = new FuncionarioDAO();

			fdao.editar(funcionario);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados editados com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

}
