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
	//CRIA��O DA VARI�VEL ITENS
	private ListDataModel<Cargo> itens;

	public ListDataModel<Cargo> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Cargo> itens) {
		this.itens = itens;
	}

	@PostConstruct
	public void prepararPesquisaCargo(){
		//TRATAMENTO DA EXCESS�O
		try{
			//CRIA��O DA VARI�VEL DAO DO TIPO CARGODAO
			CargoDAO dao = new CargoDAO();
			//CRIA��O DA VARI�VEL LISTA DO TIPO ARRAYLIST CARGO
			//QUE RECEBE O M�TODO LISTAR DA CLASSE CARGO DAO
			ArrayList<Cargo> lista = dao.listar();
			//A VARI�VEL ITENS RECEBE OS DADOS DA LISTA
			itens = new ListDataModel<Cargo>(lista);
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
}
