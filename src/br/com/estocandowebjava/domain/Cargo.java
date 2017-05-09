package br.com.estocandowebjava.domain;

public class Cargo {
	private Long codigo;
	private String descricao;
	private Double salario;
	private Long permissao;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Long getPermissao() {
		return permissao;
	}

	public void setPermissao(Long permissao) {
		this.permissao = permissao;
	}

	@Override
	public String toString() {
		String saida = codigo + " / " + descricao;
		return saida;
	}
}