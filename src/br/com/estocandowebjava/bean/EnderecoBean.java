package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.EnderecoDAO;
import br.com.estocandowebjava.domain.Endereco;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBEndereco")
@ViewScoped
public class EnderecoBean {
	private Endereco endereco;
	private ArrayList<Endereco> itens;
	private ArrayList<Endereco> itensFiltrados;

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public ArrayList<Endereco> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Endereco> itens) {
		this.itens = itens;
	}

	public ArrayList<Endereco> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Endereco> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisaEndereco() {
		try {
			EnderecoDAO dao = new EnderecoDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public void prepararNovoEndereco() {
		endereco = new Endereco();
	}

	public void novoEndereco() {
		try {
			EnderecoDAO dao = new EnderecoDAO();
			dao.salvarEndereco(endereco);
			
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Dados salvos com sucesso");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// CARREGA OS DADOS DA TABELA QUE SERÃO EXCLUIDOS
	public void excluirEndereco() {
		try {
			EnderecoDAO dao = new EnderecoDAO();
			dao.excluirEndereco(endereco);

			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Dados removidos com sucesso");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// CARREGA OS DADOS DA TABELA QUE SERÃO EDITADOS
	public void editarEndereco() {
		try {
			EnderecoDAO dao = new EnderecoDAO();
			dao.editarEndereco(endereco);

			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Dados editados com sucesso.");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}