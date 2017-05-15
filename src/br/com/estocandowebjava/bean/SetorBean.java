package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.SetorDAO;
import br.com.estocandowebjava.domain.Setor;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBSetor")
@ViewScoped
public class SetorBean {
	private Setor setor;
	private ArrayList<Setor> itens;
	private ArrayList<Setor> itensFiltrados;

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public ArrayList<Setor> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Setor> itens) {
		this.itens = itens;
	}

	public ArrayList<Setor> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Setor> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisaSetor() {
		try {
			SetorDAO dao = new SetorDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void prepararNovoSetor() {
		setor = new Setor();
	}

	public void novoSetor() {
		try {
			SetorDAO dao = new SetorDAO();
			dao.salvarSetor(setor);
			
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Dados salvos com sucesso");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// CARREGA OS DADOS DA TABELA QUE SERÃO EXCLUIDOS
	public void excluirSetor() {
		try {
			SetorDAO dao = new SetorDAO();
			dao.excluirSetor(setor);

			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Dados removidos com sucesso");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// CARREGA OS DADOS DA TABELA QUE SERÃO EDITADOS
	public void editarSetor() {
		try {
			SetorDAO dao = new SetorDAO();
			dao.editarSetor(setor);

			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Dados editados com sucesso.");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
