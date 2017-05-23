package br.com.estocandowebjava.domain;

public class Requisicao {
	private Long codigo;
	private String data;
	private Funcionario almoxarife = new Funcionario();
	private Funcionario requisitante = new Funcionario();

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
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

}
