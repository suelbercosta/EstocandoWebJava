package br.com.estocandowebjava.domain;

import java.util.*;

public class Funcionario {
	private Long matricula;
	private Long ctps;
	private Long pis;
	private String sexo;
	private String tipo_sang;
	private Date data_admissao;
	private Long cargo;
	private Long setor;
	private Long endereco;

	public Long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTipo_sang() {
		return tipo_sang;
	}

	public void setTipo_sang(String tipo_sang) {
		this.tipo_sang = tipo_sang;
	}

	public Date getData_admissao() {
		return data_admissao;
	}

	public void setData_admissao(Date data_admissao) {
		this.data_admissao = data_admissao;
	}

	public Long getCargo() {
		return cargo;
	}

	public void setCargo(Long cargo) {
		this.cargo = cargo;
	}

	public Long getSetor() {
		return setor;
	}

	public void setSetor(Long setor) {
		this.setor = setor;
	}

	public Long getEndereco() {
		return endereco;
	}

	public void setEndereco(Long endereco) {
		this.endereco = endereco;
	}

}
