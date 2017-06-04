package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.ProdutoDAO;
import br.com.estocandowebjava.dao.TipoProdutoDAO;
import br.com.estocandowebjava.domain.Produto;
import br.com.estocandowebjava.domain.TipoProduto;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean implements InterfaceBean {
	// Declaração de variáveis
	private Produto produto = new Produto();
	private ArrayList<TipoProduto> comboTipoProduto = new ArrayList<TipoProduto>();

	private ArrayList<Produto> itens;
	private ArrayList<Produto> itensFiltrados;

	// Declaração dos métodos gets e sets
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public ArrayList<TipoProduto> getComboTipoProduto() {
		return comboTipoProduto;
	}

	public void setComboTipoProduto(ArrayList<TipoProduto> comboTipoProduto) {
		this.comboTipoProduto = comboTipoProduto;
	}

	public ArrayList<Produto> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produto> itens) {
		this.itens = itens;
	}

	public ArrayList<Produto> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produto> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	// Sobreescrita do método carregarListagem() da InterfaceBean
	public void carregarListagem() {
		try {
			ProdutoDAO dao = new ProdutoDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// COMANDO PARA PREPARAR NOVO PRODUTOS
	@Override
	// Sobreescrita do método prepararNovo() da InterfaceBean
	public void prepararNovo() {
		try {
			produto = new Produto();

			TipoProdutoDAO cdao = new TipoProdutoDAO();

			comboTipoProduto = cdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	@Override
	// Sobreescrita do método novo()
	public void novo() {
		try {
			ProdutoDAO pdao = new ProdutoDAO();
			pdao.salvar(produto);

			itens = pdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados salvos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// COMANDO PARA EXCLUIR UM PRODUTOS
	@Override
	// Sobreescrita do método excluir() da InterfaceBean
	public void excluir() {
		try {
			ProdutoDAO dao = new ProdutoDAO();

			dao.excluir(produto);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados excluidos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// COMANDO PARA PREPARAR EDITAR PRODUTOS
	@Override
	// Sobreescrita do método prepararEditar() da InterfaceBean
	public void prepararEditar() {
		try {
			TipoProdutoDAO dao = new TipoProdutoDAO();

			comboTipoProduto = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	@Override
	// Sobreescrita do método editar() da InterfaceBean
	public void editar() {
		try {
			ProdutoDAO dao = new ProdutoDAO();

			dao.editar(produto);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados editados com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

}
