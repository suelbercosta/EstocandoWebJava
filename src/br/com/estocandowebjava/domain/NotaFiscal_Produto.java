package br.com.estocandowebjava.domain;

public class NotaFiscal_Produto {
	private NotaFiscal notafiscal = new NotaFiscal();
	private Produto produto = new Produto();
	
	public NotaFiscal getNotafiscal() {
		return notafiscal;
	}
	public void setNotafiscal(NotaFiscal notafiscal) {
		this.notafiscal = notafiscal;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
}
