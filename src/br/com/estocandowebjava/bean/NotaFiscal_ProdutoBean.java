package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.NotaFiscalDAO;
import br.com.estocandowebjava.dao.NotaFiscal_ProdutoDAO;
import br.com.estocandowebjava.dao.ProdutoDAO;
import br.com.estocandowebjava.domain.NotaFiscal;
import br.com.estocandowebjava.domain.NotaFiscal_Produto;
import br.com.estocandowebjava.domain.Produto;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBNotaFiscal_Produto")
@ViewScoped
public class NotaFiscal_ProdutoBean {
	private NotaFiscal_Produto notafiscal_produto;
	private ArrayList<NotaFiscal> comboNF;
	private ArrayList<Produto> comboProduto;
	
	private ArrayList<NotaFiscal_Produto> itens;
	private ArrayList<NotaFiscal_Produto> itensFiltrados;
	

	public NotaFiscal_Produto getNotafiscal_produto() {
		return notafiscal_produto;
	}

	public void setNotafiscal_produto(NotaFiscal_Produto notafiscal_produto) {
		this.notafiscal_produto = notafiscal_produto;
	}

	public ArrayList<NotaFiscal> getComboNF() {
		return comboNF;
	}

	public void setComboNF(ArrayList<NotaFiscal> comboNF) {
		this.comboNF = comboNF;
	}

	public ArrayList<Produto> getComboProduto() {
		return comboProduto;
	}

	public void setComboProduto(ArrayList<Produto> comboProduto) {
		this.comboProduto = comboProduto;
	}

	public ArrayList<NotaFiscal_Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<NotaFiscal_Produto> itens) {
		this.itens = itens;
	}

	public ArrayList<NotaFiscal_Produto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<NotaFiscal_Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	// MÉTODO PARA PREPARAR A PESQUISA DE NOTA FISCAL/PRODUTO
	public void carregarListagem() {
		try {
			NotaFiscal_ProdutoDAO dao = new NotaFiscal_ProdutoDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// MÉTODO DE PREPARAÇÃO PARA INSERIR NOTA FISCAL/PRODUTO
	public void prepararNovo() {
		try {
			notafiscal_produto = new NotaFiscal_Produto();

			NotaFiscalDAO dao = new NotaFiscalDAO();

			comboNF = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
		
		try {
			notafiscal_produto = new NotaFiscal_Produto();

			ProdutoDAO dao = new ProdutoDAO();

			comboProduto = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// MÉTODO PARA INSERIR NOTA FISCAL/PRODUTO
	public void novo() {
		try {
			NotaFiscal_ProdutoDAO dao = new NotaFiscal_ProdutoDAO();
			dao.salvar(notafiscal_produto);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados salvos com sucesso");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// MÉTODO PARA EXCLUIR NOTA FISCAL/PRODUTO
	public void excluir() {
		try {
			NotaFiscal_ProdutoDAO dao = new NotaFiscal_ProdutoDAO();
			dao.excluir(notafiscal_produto);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados removidos com sucesso");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	// MÉTODO PARA PREPARAR A EDIÇÃO DE NOTA FISCAL/PRODUTO
	public void prepararEditar() {
		try {
			NotaFiscalDAO dao = new NotaFiscalDAO();

			comboNF = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
		
		try {
			ProdutoDAO dao = new ProdutoDAO();

			comboProduto = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	// MÉTODO PARA EDITAR NOTA FISCAL/PRODUTO
	public void editar() {
		try {
			NotaFiscal_ProdutoDAO dao = new NotaFiscal_ProdutoDAO();
			dao.editar(notafiscal_produto);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados editados com sucesso.");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
