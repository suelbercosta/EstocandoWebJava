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
	// CRIA��O DA VARI�VEL CARGO PARA CADASTRO, ALTERA��O E EXCLUS�O
	private Cargo cargo;
	// CRIA��O DA VARI�VEL ITENS
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
		// TRATAMENTO DA EXCESS�O
		try {
			// CRIA��O DA VARI�VEL DAO DO TIPO CARGODAO
			CargoDAO dao = new CargoDAO();
			// CRIA��O DA VARI�VEL LISTA DO TIPO ARRAYLIST CARGO
			// QUE RECEBE O M�TODO LISTAR DA CLASSE CARGO DAO
			ArrayList<Cargo> lista = dao.listar();
			// A VARI�VEL ITENS RECEBE OS DADOS DA LISTA
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
