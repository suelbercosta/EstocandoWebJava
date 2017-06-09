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
	// Declara��o de vari�veis
	private Long codigo;
	private Double quant;
	private ItemEstoque itemEstoque = new ItemEstoque();
	private ArrayList<Produto> comboProduto;
	private ArrayList<Requisicao> comboRequisicao;

	private ArrayList<ItemEstoque> itens;
	private ArrayList<ItemEstoque> itensFiltrados;

	// Declara��o dos m�todos gets e sets
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

	// M�TODO CARREGAR LISTAGEM
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

			ProdutoDAO pdao = new ProdutoDAO();

			comboProduto = pdao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	// M�TODO NOVO
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

	//M�TODO EDITAR
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
	
	//M�TODO PARA VALIDAR A QUANTIDADE SOLICITADA
	public void validQuant() throws SQLException {
		//ATRIBUI��O DOS VALORES DIGITADOS �S VARI�VEIS LOCAIS
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
