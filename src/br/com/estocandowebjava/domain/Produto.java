package br.com.estocandowebjava.domain;

public class Produto {
	private Long codigo;
	private String descricao;
	private Long quantidade;
	private String unid_med;
	private Double valor;
	private String data_val;
	private String data_aquis;
	private Long quant_minima;
	private Double peso;
	private String cor;
	private TipoProduto tipo_produto = new TipoProduto();

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

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public String getUnid_med() {
		return unid_med;
	}

	public void setUnid_med(String unid_med) {
		this.unid_med = unid_med;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getData_val() {
		return data_val;
	}

	public void setData_val(String data_val) {
		this.data_val = data_val;
	}

	public String getData_aquis() {
		return data_aquis;
	}

	public void setData_aquis(String data_aquis) {
		this.data_aquis = data_aquis;
	}

	public Long getQuant_minima() {
		return quant_minima;
	}

	public void setQuant_minima(Long quant_minima) {
		this.quant_minima = quant_minima;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public TipoProduto getTipo_produto() {
		return tipo_produto;
	}

	public void setTipo_produto(TipoProduto tipo_produto) {
		this.tipo_produto = tipo_produto;
	}

}