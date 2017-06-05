package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.EnderecoDAO;
import br.com.estocandowebjava.dao.PessoaJurudicaDAO;
import br.com.estocandowebjava.domain.Endereco;
import br.com.estocandowebjava.domain.Fornecedor;
import br.com.estocandowebjava.domain.Pessoa_Juridica;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBFornecedorPJ")
@ViewScoped
public class FornecedorPJBean {
	private Fornecedor fornecedor = new Fornecedor();
	private Endereco endereco = new Endereco();
	private Pessoa_Juridica pJuridica = new Pessoa_Juridica();
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

	public Pessoa_Juridica getpJuridica() {
		return pJuridica;
	}

	public void setpJuridica(Pessoa_Juridica pJuridica) {
		this.pJuridica = pJuridica;
	}

	// MÉTODO PARA CARREGAR A LISTAGEM DOS FORNECEDORES
	public void carregarListagem() {
		try {
			PessoaJurudicaDAO dao = new PessoaJurudicaDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// MÉTODO PARA PREPARAR NOVO FORNECEDOR
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

	public void novo() {
		try {
			PessoaJurudicaDAO fdao = new PessoaJurudicaDAO();
			fornecedor.setEndereco(endereco);
			fornecedor.setPessoajuridica(pJuridica);
			fdao.salvar(fornecedor);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados salvos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// COMANDO PARA EXCLUIR UM FORNECEDORES
	public void excluir() {
		try {
			PessoaJurudicaDAO fdao = new PessoaJurudicaDAO();

			fdao.excluir(fornecedor);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados excluidos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// COMANDO PARA PREPARAR EDITAR FORNECEDORES
	public void prepararEditar() {
		try {
			EnderecoDAO edao = new EnderecoDAO();

			comboEndereco = edao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	public void editar() {
		try {
			PessoaJurudicaDAO fdao = new PessoaJurudicaDAO();

			fdao.editar(fornecedor);

			itens = fdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados editados com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
}