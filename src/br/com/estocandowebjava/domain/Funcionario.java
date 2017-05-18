package br.com.estocandowebjava.domain;

public class Funcionario {
	private Long matricula;
	private String nome;
	private String cpf;
	private String rg;
	private String data_nasc;
	private String sexo;
	private Long ctps;
	private Long pis;
	private String data_admissao;
	private String tipo_sang;
	private Cargo cargo;
	private String login;
	private String senha;
	private Setor setor;
	private Endereco endereco;
	
	public Long getMatricula() {
		return matricula;
	}
	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getData_nasc() {
		return data_nasc;
	}
	public void setData_nasc(String data_nasc) {
		this.data_nasc = data_nasc;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Long getCtps() {
		return ctps;
	}
	public void setCtps(Long ctps) {
		this.ctps = ctps;
	}
	public Long getPis() {
		return pis;
	}
	public void setPis(Long pis) {
		this.pis = pis;
	}
	public String getData_admissao() {
		return data_admissao;
	}
	public void setData_admissao(String data_admissao) {
		this.data_admissao = data_admissao;
	}
	public String getTipo_sang() {
		return tipo_sang;
	}
	public void setTipo_sang(String tipo_sang) {
		this.tipo_sang = tipo_sang;
	}
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Setor getSetor() {
		return setor;
	}
	public void setSetor(Setor setor) {
		this.setor = setor;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
}
