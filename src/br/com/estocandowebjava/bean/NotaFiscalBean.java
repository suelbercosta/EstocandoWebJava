package br.com.estocandowebjava.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.dao.FornecedorDAO;
import br.com.estocandowebjava.dao.NotaFiscalDAO;
import br.com.estocandowebjava.domain.Fornecedor;
import br.com.estocandowebjava.domain.NotaFiscal;
import br.com.estocandowebjava.util.JSFUtil;

@ManagedBean(name = "MBNotaFiscal")
@ViewScoped
public class NotaFiscalBean implements InterfaceBean {
	// Declara��o de vari�veis
	private NotaFiscal notaFiscal = new NotaFiscal();
	private ArrayList<Fornecedor> comboFornecedor;

	private ArrayList<NotaFiscal> itens;
	private ArrayList<NotaFiscal> itensFiltrados;

	// Declara��o dos m�todos gets e sets
	public NotaFiscal getNotaFiscal() {
		return notaFiscal;
	}

	public void setNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal = notaFiscal;
	}

	public ArrayList<Fornecedor> getComboFornecedor() {
		return comboFornecedor;
	}

	public void setComboFornecedor(ArrayList<Fornecedor> comboFornecedor) {
		this.comboFornecedor = comboFornecedor;
	}

	public ArrayList<NotaFiscal> getItens() {
		return itens;
	}

	public void setItens(ArrayList<NotaFiscal> itens) {
		this.itens = itens;
	}

	public ArrayList<NotaFiscal> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<NotaFiscal> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	// Sobreescrita do m�todo carregarListagem() da InterfaceBean
	public void carregarListagem() {
		try {
			NotaFiscalDAO dao = new NotaFiscalDAO();
			itens = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// COMANDO PARA PREPARAR NOVO PRODUTOS
	@Override
	// Sobreescrita do m�todo prepararNovo() da InterfaceBean
	public void prepararNovo() {
		try {
			notaFiscal = new NotaFiscal();

			FornecedorDAO fdao = new FornecedorDAO() {

				@Override
				public void salvar(Fornecedor f) throws SQLException {
					// TODO Auto-generated method stub

				}

				@Override
				public ArrayList<Fornecedor> listar() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void excluir(Fornecedor f) throws SQLException {
					// TODO Auto-generated method stub

				}

				@Override
				public void editar(Fornecedor f) throws SQLException {
					// TODO Auto-generated method stub

				}
			};

			comboFornecedor = fdao.listar();
			System.out.println(comboFornecedor);
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	@Override
	// Sobreescrita do m�todo novo()
	public void novo() {
		try {
			NotaFiscalDAO nfdao = new NotaFiscalDAO();
			nfdao.salvar(notaFiscal);

			itens = nfdao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados salvos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// COMANDO PARA EXCLUIR UM PRODUTOS
	@Override
	// Sobreescrita do m�todo excluir() da InterfaceBean
	public void excluir() {
		try {
			NotaFiscalDAO dao = new NotaFiscalDAO();

			dao.excluir(notaFiscal);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados excluidos com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	// COMANDO PARA PREPARAR EDITAR PRODUTOS
	@Override
	// Sobreescrita do m�todo prepararEditar() da InterfaceBean
	public void prepararEditar() {
		try {
			FornecedorDAO dao = new FornecedorDAO() {

				@Override
				public void salvar(Fornecedor f) throws SQLException {
					// TODO Auto-generated method stub

				}

				@Override
				public ArrayList<Fornecedor> listar() throws SQLException {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void excluir(Fornecedor f) throws SQLException {
					// TODO Auto-generated method stub

				}

				@Override
				public void editar(Fornecedor f) throws SQLException {
					// TODO Auto-generated method stub

				}
			};

			comboFornecedor = dao.listar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}

	}

	@Override
	// Sobreescrita do m�todo editar() da InterfaceBean
	public void editar() {
		try {
			NotaFiscalDAO dao = new NotaFiscalDAO();

			dao.editar(notaFiscal);

			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Dados editados com sucesso!");
		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

}
