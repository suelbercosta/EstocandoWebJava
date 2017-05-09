package br.com.estocandowebjava.domain;

import java.util.*;

public class Requisicao {
	private Long cod_requisicao;
	private Date data;
	private Funcionario almoxarife;
	private Funcionario requisitante;
	private Funcionario funcionario;

	public Long getCod_requisicao() {
		return cod_requisicao;
	}

	public void setCod_requisicao(Long cod_requisicao) {
		this.cod_requisicao = cod_requisicao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Funcionario getAlmoxarife() {
		return almoxarife;
	}

	public void setAlmoxarife(Funcionario almoxarife) {
		this.almoxarife = almoxarife;
	}

	public Funcionario getRequisitante() {
		return requisitante;
	}

	public void setRequisitante(Funcionario requisitante) {
		this.requisitante = requisitante;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}
