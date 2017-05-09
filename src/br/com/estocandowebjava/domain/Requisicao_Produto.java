package br.com.estocandowebjava.domain;

public class Requisicao_Produto {
	private Produto cod_prod;
	private Requisicao cod_requisiscao;
	private Long quantidade;
	public Produto getCod_prod() {
		return cod_prod;
	}
	public void setCod_prod(Produto cod_prod) {
		this.cod_prod = cod_prod;
	}
	public Requisicao getCod_requisiscao() {
		return cod_requisiscao;
	}
	public void setCod_requisiscao(Requisicao cod_requisiscao) {
		this.cod_requisiscao = cod_requisiscao;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	
	
}
