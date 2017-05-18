package br.com.estocandowebjava.domain;

public class Pessoa_Juridica extends Fornecedor{
	private Long cnpj;
	private String razao_social;
	private Long inscricao_estadual;
	private Fornecedor fornecedor;

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazao_social() {
		return razao_social;
	}

	public void setRazao_social(String razao_social) {
		this.razao_social = razao_social;
	}

	public Long getInscricao_estadual() {
		return inscricao_estadual;
	}

	public void setInscricao_estadual(Long inscricao_estadual) {
		this.inscricao_estadual = inscricao_estadual;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
