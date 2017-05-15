package br.com.estocandowebjava.domain;

public class Setor {
	private Long codigo;
	private String descricao;
	private String andar;

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

	public String getAndar() {
		return andar;
	}

	public void setAndar(String andar) {
		this.andar = andar;
	}
	
	@Override
	public String toString() {
		String saida = codigo + " / " + descricao;
		return saida;
	}

}
