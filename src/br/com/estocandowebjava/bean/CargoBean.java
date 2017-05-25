package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.CargoDAO;
import br.com.estocandowebjava.domain.Cargo;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBCargo")
@ViewScoped
public class CargoBean {
	private Cargo cargo;
	private ArrayList<Cargo> itens;
	private ArrayList<Cargo> itensFiltrados;

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public ArrayList<Cargo> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Cargo> itens) {
		this.itens = itens;
	}

	public ArrayList<Cargo> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Cargo> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	// MÉTODO PARA CONSULTA
	public void prepararPesquisaCargo() {
		try {
			CargoDAO dao = new CargoDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// MÉTODO PARA NOVO CARGO
	public void prepararNovoCargo() {
		cargo = new Cargo();
	}

	public void novoCargo() {
		try {
			CargoDAO dao = new CargoDAO();
			dao.salvarCargo(cargo);
			
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Dados salvos com sucesso");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// CARREGA OS DADOS DA TABELA QUE SERÃO EXCLUIDOS
	public void excluirCargo() {
		try {
			CargoDAO dao = new CargoDAO();
			dao.excluirCargo(cargo);

			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Dados removidos com sucesso");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// CARREGA OS DADOS DA TABELA QUE SERÃO EDITADOS
	public void editarCargo() {
		try {
			CargoDAO dao = new CargoDAO();
			dao.editarCargo(cargo);

			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Dados editados com sucesso.");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
