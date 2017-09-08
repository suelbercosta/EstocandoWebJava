package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.NotaFiscalDAO;
import br.com.estocandowebjava.dao.NotaFiscal_ProdutoDAO;
import br.com.estocandowebjava.dao.PessoaFisicaDAO;
import br.com.estocandowebjava.dao.PessoaJurudicaDAO;
import br.com.estocandowebjava.dao.ProdutoDAO;
import br.com.estocandowebjava.domain.Fornecedor;
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
	private ArrayList<Fornecedor> comboPF;
	private ArrayList<Fornecedor> comboPJ;
	private boolean mostrarpf;
	private boolean mostrarpj;
	private boolean txtFornPF;
	private String valor;
	
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
	
	public ArrayList<Fornecedor> getComboPF() {
		return comboPF;
	}
	
	public void setComboPF(ArrayList<Fornecedor> comboPF) {
		this.comboPF = comboPF;
	}
	
	public ArrayList<Fornecedor> getComboPJ() {
		return comboPJ;
	}
	
	public void setComboPJ(ArrayList<Fornecedor> comboPJ) {
		this.comboPJ = comboPJ;
	}
	
	public boolean isMostrarpf() {
		return mostrarpf;
	}

	public void setMostrarpf(boolean mostrarpf) {
		this.mostrarpf = mostrarpf;
	}
	
	public boolean isMostrarpj() {
		return mostrarpj;
	}

	public void setMostrarpj(boolean mostrarpj) {
		this.mostrarpj = mostrarpj;
	}

	public String getValor() {
		return valor;
	}
	
	public void setValor(String valor) {
		this.valor = valor;
	}

	public boolean isTxtFornPF() {
		return txtFornPF;
	}

	public void setTxtFornPF(boolean txtFornPF) {
		this.txtFornPF = txtFornPF;
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

			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();

			comboPF = pfdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
		
		try {
			notafiscal_produto = new NotaFiscal_Produto();

			PessoaJurudicaDAO pjdao = new PessoaJurudicaDAO();

			comboPJ = pjdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
		
		try {
			notafiscal_produto = new NotaFiscal_Produto();

			ProdutoDAO pdao = new ProdutoDAO();

			comboProduto = pdao.listar();
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
			PessoaFisicaDAO pfdao = new PessoaFisicaDAO();

			comboPF = pfdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
		
		try {
			PessoaJurudicaDAO pjdao = new PessoaJurudicaDAO();

			comboPJ = pjdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
		
		try {
			ProdutoDAO pdao = new ProdutoDAO();

			comboProduto = pdao.listar();
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
	
	public void pessoa(){
		valor = getValor();
		System.out.println(valor);
		if (valor == "pf"){
			mostrarpf = true;
			mostrarpj = false;
		}
		
		if (valor == "pj"){
			mostrarpj = true;
			mostrarpf = false;
		}
	}
	
	public void tipoPessoa() {
	     
	}
}
