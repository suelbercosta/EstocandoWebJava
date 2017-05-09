package br.com.estocandowebjava.domain;

public class Fornecedor {
	private Long cod_forn;
	private Long tipo_pessoa;
	private Long telefone;
	private String email;
	private Long fax;
	private Endereco endereco;

	public Long getCod_forn() {
		return cod_forn;
	}

	public void setCod_forn(Long cod_forn) {
		this.cod_forn = cod_forn;
	}

	public Long getTipo_pessoa() {
		return tipo_pessoa;
	}

	public void setTipo_pessoa(Long tipo_pessoa) {
		this.tipo_pessoa = tipo_pessoa;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getFax() {
		return fax;
	}

	public void setFax(Long fax) {
		this.fax = fax;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
