package br.com.estocandowebjava.domain;

public class NotaFiscal {
	private Long codigo;
	private double numero_nota;
	private Fornecedor fornecedor = new Fornecedor();
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public double getNumeroNota() {
		return numero_nota;
	}
	public void setNumeroNota(double numero_nota) {
		this.numero_nota = numero_nota;
	}
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	
}
