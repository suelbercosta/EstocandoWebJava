package br.com.estocandowebjava.bean;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.domain.Funcionario;

@ManagedBean(name = "MBLogin")
@ViewScoped
public class LoginBean {
	private Funcionario login;
	private Funcionario senha;
	private ArrayList<Funcionario> itens;
	private ArrayList<Funcionario> itensFiltrados;

	public Funcionario getLogin() {
		return login;
	}

	public void setLogin(Funcionario login) {
		this.login = login;
	}

	public Funcionario getSenha() {
		return senha;
	}

	public void setSenha(Funcionario senha) {
		this.senha = senha;
	}

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

	@PostConstruct
	public void login() {

	}
}
