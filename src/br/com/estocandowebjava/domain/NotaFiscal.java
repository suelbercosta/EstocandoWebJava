package br.com.estocandowebjava.domain;

public class NotaFiscal {
	private Long codigo;
	private Long numero_nota;
	private Fornecedor forn = new Fornecedor();
	private Fornecedores fornecedores = new Fornecedores();
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Fornecedores getFornecedor() {
		return fornecedores;
	}
	public void setFornecedor(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}
	public Fornecedor getForn() {
		return forn;
	}
	public void setForn(Fornecedor forn) {
		this.forn = forn;
	}
	public Long getNumero_nota() {
		return numero_nota;
	}
	public void setNumero_nota(Long numero_nota) {
		this.numero_nota = numero_nota;
	}
	
	
}
