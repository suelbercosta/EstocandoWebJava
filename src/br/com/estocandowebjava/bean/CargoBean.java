package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.estocandowebjava.dao.CargoDAO;
import br.com.estocandowebjava.domain.Cargo;

@ManagedBean(name = "MBCargo")
@ViewScoped
public class CargoBean {
	//CRIAÇÃO DA VARIÁVEL ITENS
	private ListDataModel<Cargo> itens;

	public ListDataModel<Cargo> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Cargo> itens) {
		this.itens = itens;
	}

	@PostConstruct
	public void prepararPesquisaCargo(){
		//TRATAMENTO DA EXCESSÃO
		try{
			//CRIAÇÃO DA VARIÁVEL DAO DO TIPO CARGODAO
			CargoDAO dao = new CargoDAO();
			//CRIAÇÃO DA VARIÁVEL LISTA DO TIPO ARRAYLIST CARGO
			//QUE RECEBE O MÉTODO LISTAR DA CLASSE CARGO DAO
			ArrayList<Cargo> lista = dao.listar();
			//A VARIÁVEL ITENS RECEBE OS DADOS DA LISTA
			itens = new ListDataModel<Cargo>(lista);
		} catch (SQLException ex){
			ex.printStackTrace();
		}
	}
}
