package br.com.estocandowebjava.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import br.com.estocandowebjava.factoty.ConexaoFactory;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBItemEstoque")
@ViewScoped
public class ItemEstoqueBean {
	// Declaração de variáveis
	private Long codigo;
	private Double quant;
	private Produto produto;
	private Requisicao requisicao;
	private ItemEstoque itemEstoque = new ItemEstoque();
	private ArrayList<ItemEstoque> comboFiltrar;
	private ArrayList<Produto> comboProduto;
	private ArrayList<Requisicao> comboRequisicao;

	private ArrayList<ItemEstoque> itens;
	private ArrayList<ItemEstoque> itensFiltrados;

	// Declaração dos métodos gets e sets
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public Double getQuant() {
		return quant;
	}

	public void setQuant(Double quant) {
		this.quant = quant;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public Requisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}
	
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
	
	public ArrayList<ItemEstoque> getComboFiltrar() {
		return comboFiltrar;
	}

	public void setComboFiltrar(ArrayList<ItemEstoque> comboFiltrar) {
		this.comboFiltrar = comboFiltrar;
	}

	// MÉTODO CARREGAR LISTAGEM
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
	public void prepararNovo() {
		try {
			itemEstoque = new ItemEstoque();

			ItemEstoqueDAO iedao = new ItemEstoqueDAO();

			comboFiltrar = iedao.filtrar(); 
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
		
		try {
			itemEstoque = new ItemEstoque();

			ProdutoDAO pdao = new ProdutoDAO();

			comboProduto = pdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
		
		try {
			itemEstoque = new ItemEstoque();

			RequisicaoDAO rdao = new RequisicaoDAO();
			
			comboRequisicao = rdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	// MÉTODO NOVO
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

	//MÉTODO EDITAR
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
	
	//MÉTODO PARA VALIDAR A QUANTIDADE SOLICITADA
	public void validQuant() throws SQLException {
		//ATRIBUIÇÃO DOS VALORES DIGITADOS ÀS VARIÁVEIS LOCAIS
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO Produto (codigo) VALUES (?) ");
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, getCodigo());
		System.out.println("Suelber Costa");
		System.out.println(getCodigo());
	}
	
	public void quantidade() throws SQLException {
		//CONSULTA NO BANCO PARA COMPARAR COM O VALOR DIGITADO
		StringBuilder sql1 = new StringBuilder();
		sql1.append("SELECT quantidade FROM Produto WHERE codigo = '" + getCodigo() + "' ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando1 = conexao.prepareStatement(sql1.toString());
		
		ResultSet resultado = comando1.executeQuery();
		
		setQuant(resultado.getDouble("quantidade"));
		
		System.out.println(getQuant());
	}

}
