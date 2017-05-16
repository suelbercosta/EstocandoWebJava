package br.com.estocandowebjava.domain;

import java.util.*;

public class Produto {
	private Long codigo;
	private String descricao;
	private Long quantidade;
	private String unid_med;
	private Double valor_unit;
	private Date data_valid;
	private Date data_aquisicao;
	private Long quant_minima;
	private Double peso;
	private String cor;
	private TipoProduto tipo_produto;

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

	public Double getValor_unit() {
		return valor_unit;
	}

	public void setValor_unit(Double valor_unit) {
		this.valor_unit = valor_unit;
	}

	public Date getData_valid() {
		return data_valid;
	}

	public void setData_valid(Date data_valid) {
		this.data_valid = data_valid;
	}

	public Date getData_aquisicao() {
		return data_aquisicao;
	}

	public void setData_aquisicao(Date data_aquisicao) {
		this.data_aquisicao = data_aquisicao;
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