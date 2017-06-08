package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.ItemEstoqueDAO;
import br.com.estocandowebjava.dao.ProdutoDAO;
import br.com.estocandowebjava.dao.RequisicaoDAO;
import br.com.estocandowebjava.domain.ItemEstoque;
import br.com.estocandowebjava.domain.Produto;
import br.com.estocandowebjava.domain.Requisicao;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBItemEstoque")
@ViewScoped
public class ItemEstoqueBean implements InterfaceBean {
	// Declaração de variáveis
	private ItemEstoque itemEstoque = new ItemEstoque();
	private ArrayList<Produto> comboProduto;
	private ArrayList<Requisicao> comboRequisicao;

	private ArrayList<ItemEstoque> itens;
	private ArrayList<ItemEstoque> itensFiltrados;

	// Declaração dos métodos gets e sets
	public ItemEstoque getItemEstoque() {
		return itemEstoque;
	}

	public void setItemEstoque(ItemEstoque itemEstoque) {
		this.itemEstoque = itemEstoque;
	}

	public ArrayList<Produto> getComboProduto() {
		return comboProduto;
	}
	
	public void setComboProduto(ArrayList<Produto> comboProduto) {
		this.comboProduto = comboProduto;
	}

	public ArrayList<Requisicao> getComboRequisicao() {
		return comboRequisicao;
	}

	public void setComboRequisicao(ArrayList<Requisicao> comboRequisicao) {
		this.comboRequisicao = comboRequisicao;
	}

	public ArrayList<ItemEstoque> getItens() {
		return itens;
	}

	public void setItens(ArrayList<ItemEstoque> itens) {
		this.itens = itens;
	}

	public ArrayList<ItemEstoque> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<ItemEstoque> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	// Sobreescrita do método carregarListagem() da InterfaceBean
	@Override
	public void carregarListagem() {
		try {
			ItemEstoqueDAO dao = new ItemEstoqueDAO();
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
			itemEstoque = new ItemEstoque();

			ProdutoDAO pdao = new ProdutoDAO();

			comboProduto = pdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	@Override
	// Sobreescrita do método novo()
	public void novo() {
		try {
			ItemEstoqueDAO dao = new ItemEstoqueDAO();
			dao.salvar(itemEstoque);

			itens = dao.listar();

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
			ItemEstoqueDAO dao = new ItemEstoqueDAO();

			dao.excluir(itemEstoque);

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
			ProdutoDAO pdao = new ProdutoDAO();

			comboProduto = pdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
		
		try {
			RequisicaoDAO rdao = new RequisicaoDAO();

			comboRequisicao = rdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	@Override
	// Sobreescrita do método editar() da InterfaceBean
	public void editar() {
		try {
			ItemEstoqueDAO dao = new ItemEstoqueDAO();

			dao.editar(itemEstoque);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados editados com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	public void validQuant() {
		try {
			ItemEstoqueDAO dao = new ItemEstoqueDAO();
			
			dao.validarQuantidade();

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados editados com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
