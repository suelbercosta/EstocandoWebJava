package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.LoginDAO;
import br.com.estocandowebjava.domain.Funcionario;
import br.com.estocandowebjava.domain.Login;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBLogin")
@ViewScoped
public class LoginBean {
	private Login login;
	private ArrayList<Funcionario> comboFuncionario;
	
	public ArrayList<Funcionario> getComboFuncionario() {
		return comboFuncionario;
	}

	public void setComboFuncionario(ArrayList<Funcionario> comboFuncionario) {
		this.comboFuncionario = comboFuncionario;
	}

	public ArrayList<Login> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Login> itens) {
		this.itens = itens;
	}

	public ArrayList<Login> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Login> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}


	private ArrayList<Login> itens;
	private ArrayList<Login> itensFiltrados;

	//MÉTODOS GETS E SETS
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	

	@PostConstruct
	public void fazerLogin() throws SQLException {
		login = new Login();
		LoginDAO dao = new LoginDAO();
		dao.validarLogin(login);
	}
}
