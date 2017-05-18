package br.com.estocandowebjava.bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.estocandowebjava.domain.Fornecedor;

@ManagedBean(name = "MBFornecedor")
@ViewScoped
public class FornecedorBean {
	private ArrayList<Fornecedor> itens;
}
