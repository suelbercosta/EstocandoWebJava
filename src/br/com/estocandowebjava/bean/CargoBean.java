package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.estocandowebjava.dao.CargoDAO;
import br.com.estocandowebjava.domain.Cargo;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBCargo")
@ViewScoped
public class CargoBean {
	// CRIAÇÃO DA VARIÁVEL CARGO PARA CADASTRO, ALTERAÇÃO E EXCLUSÃO
	private Cargo cargo;
	// CRIAÇÃO DA VARIÁVEL ITENS
	private ListDataModel<Cargo> itens;

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public ListDataModel<Cargo> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Cargo> itens) {
		this.itens = itens;
	}

	@PostConstruct
	public void prepararPesquisaCargo() {
		// TRATAMENTO DA EXCESSÃO
		try {
			// CRIAÇÃO DA VARIÁVEL DAO DO TIPO CARGODAO
			CargoDAO dao = new CargoDAO();
			// CRIAÇÃO DA VARIÁVEL LISTA DO TIPO ARRAYLIST CARGO
			// QUE RECEBE O MÉTODO LISTAR DA CLASSE CARGO DAO
			ArrayList<Cargo> lista = dao.listar();
			// A VARIÁVEL ITENS RECEBE OS DADOS DA LISTA
			itens = new ListDataModel<Cargo>(lista);
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void prepararNovoCargo() {
		cargo = new Cargo();
	}

	public void novoCargo() {
		try {
			CargoDAO dao = new CargoDAO();
			dao.salvarCargo(cargo);

			ArrayList<Cargo> lista = dao.listar();
			itens = new ListDataModel<Cargo>(lista);
			JSFUtil.adicionarMensagemSucesso("Dados salvos com sucesso");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void prepararExcluirCargo() {
		cargo = itens.getRowData(); // getRowData exclui o cargo clicado
	}

	public void excluirCargo() {
		try {
			CargoDAO dao = new CargoDAO();
			dao.excluirCargo(cargo);

			ArrayList<Cargo> lista = dao.listar();
			itens = new ListDataModel<Cargo>(lista);
			JSFUtil.adicionarMensagemSucesso("Dados removidos com sucesso");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
