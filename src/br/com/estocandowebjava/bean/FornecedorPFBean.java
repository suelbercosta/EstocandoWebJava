package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.EnderecoDAO;
import br.com.estocandowebjava.dao.PessoaFisicaDAO;
import br.com.estocandowebjava.domain.Endereco;
import br.com.estocandowebjava.domain.Fornecedor;
import br.com.estocandowebjava.domain.Pessoa_Fisica;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBFornecedorPF")
@ViewScoped
public class FornecedorPFBean implements InterfaceBean {
	private Fornecedor fornecedor = new Fornecedor();
	private Endereco endereco = new Endereco();
	private Pessoa_Fisica pFisica = new Pessoa_Fisica();
	private ArrayList<Endereco> comboEndereco = new ArrayList<Endereco>();
	

	private ArrayList<Fornecedor> itens;
	private ArrayList<Fornecedor> itensFiltrados;

	public ArrayList<Fornecedor> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedor> itens) {
		this.itens = itens;
	}

	public ArrayList<Fornecedor> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fornecedor> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public ArrayList<Endereco> getComboEndereco() {
		return comboEndereco;
	}

	public void setComboEndereco(ArrayList<Endereco> comboEndereco) {
		this.comboEndereco = comboEndereco;
	}

	public Pessoa_Fisica getpFisica() {
		return pFisica;
	}

	public void setpFisica(Pessoa_Fisica pFisica) {
		this.pFisica = pFisica;
	}

	// M�TODO PARA CARREGAR A LISTAGEM DOS FORNECEDORES
	@Override
	public void carregarListagem() {
		try {
			PessoaFisicaDAO dao = new PessoaFisicaDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// M�TODO PARA PREPARAR NOVO FORNECEDOR
	@Override
	public void prepararNovo() {
		try {
			fornecedor = new Fornecedor();

			EnderecoDAO edao = new EnderecoDAO();

			comboEndereco = edao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	// M�TODO PARA INSERIR NOVO FORNECEDOR
	@Override
	public void novo() {
		try {
			PessoaFisicaDAO fdao = new PessoaFisicaDAO();
			fornecedor.setEndereco(endereco);
			fornecedor.setPessoafisica(pFisica);
			fdao.salvar(fornecedor);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados salvos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// COMANDO PARA EXCLUIR UM FORNECEDORES
	@Override
	public void excluir() {
		try {
			PessoaFisicaDAO fdao = new PessoaFisicaDAO();

			fdao.excluir(fornecedor);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados excluidos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// COMANDO PARA PREPARAR EDITAR FORNECEDORES
	@Override
	public void prepararEditar() {
		try {
			EnderecoDAO edao = new EnderecoDAO();

			comboEndereco = edao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	@Override
	public void editar() {
		try {
			PessoaFisicaDAO fdao = new PessoaFisicaDAO();
			fornecedor.setEndereco(endereco);
			fornecedor.setPessoafisica(pFisica);
			fdao.editar(fornecedor);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados editados com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
}