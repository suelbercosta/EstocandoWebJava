package br.com.estocandowebjava.domain;

public class Fornecedor {
	private Long codigo;
	private String tipo_pessoa;
	private String telefone;
	private String email;
	private String fax;
	private Endereco endereco = new Endereco();
	private Pessoa_Fisica pessoafisica;
	private Pessoa_Juridica pessoajuridica;
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getTipo_pessoa() {
		return tipo_pessoa;
	}
	public void setTipo_pessoa(String tipo_pessoa) {
		this.tipo_pessoa = tipo_pessoa;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public Pessoa_Fisica getPessoafisica() {
		return pessoafisica;
	}
	public void setPessoafisica(Pessoa_Fisica pessoafisica) {
		this.pessoafisica = pessoafisica;
	}
	public Pessoa_Juridica getPessoajuridica() {
		return pessoajuridica;
	}
	public void setPessoajuridica(Pessoa_Juridica pessoajuridica) {
		this.pessoajuridica = pessoajuridica;
	}

	
	
}
