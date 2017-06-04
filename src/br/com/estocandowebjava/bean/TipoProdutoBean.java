package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.TipoProdutoDAO;
import br.com.estocandowebjava.domain.TipoProduto;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBTipoProduto")
@ViewScoped
public class TipoProdutoBean {
	private TipoProduto tipoproduto;
	private ArrayList<TipoProduto> itens;
	private ArrayList<TipoProduto> itensFiltrados;

	public TipoProduto getTipoProduto() {
		return tipoproduto;
	}

	public void setTipoProduto(TipoProduto tipoproduto) {
		this.tipoproduto = tipoproduto;
	}

	public ArrayList<TipoProduto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<TipoProduto> itens) {
		this.itens = itens;
	}

	public ArrayList<TipoProduto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<TipoProduto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	// MÉTODO PARA PREPARAR A PESQUISA DOS TIPO DE PRODUTOS
	public void prepararPesquisaTipoProduto() {
		try {
			TipoProdutoDAO dao = new TipoProdutoDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// MÉTODO DE PREPARAÇÃO PARA INSERIR UM NOVO TIPO DO PRODUTO
	public void prepararNovoTipoProduto() {
		tipoproduto = new TipoProduto();
	}

	// MÉTODO PARA INSERIR UM NOVO TIPO DE PRODUTO
	public void novoTipoProduto() {
		try {
			TipoProdutoDAO dao = new TipoProdutoDAO();
			dao.salvarTipoProduto(tipoproduto);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados salvos com sucesso");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// MÉTODO PARA EXCLUIR UM TIPO DE PRODUTO
	public void excluirTipoProduto() {
		try {
			TipoProdutoDAO dao = new TipoProdutoDAO();
			dao.excluirTipoProduto(tipoproduto);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados removidos com sucesso");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// MÉTODO PARA EDITAR UM TIPO DE PRODUTO
	public void editarTipoProduto() {
		try {
			TipoProdutoDAO dao = new TipoProdutoDAO();
			dao.editarTipoProduto(tipoproduto);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados editados com sucesso.");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
