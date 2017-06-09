package br.com.estocandowebjava.domain;

public class ItemEstoque {
	//DECLARAÇÃO DE VARIÁVEIS
	private Requisicao requisicao = new Requisicao();
	private Produto produto = new Produto();
	private Double quantidade;
	
	//DECLARAÇÃO DOS MÉTODOS
	public Requisicao getRequisicao() {
		return requisicao;
	}
	public void setRequisicao(Requisicao requisicao) {
		this.requisicao = requisicao;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
}
