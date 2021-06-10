package br.com.academia.model;

public class Modalidade {

	private Long codigoModalidade;
	private String descricao;

	public Modalidade(Long codigoModalidade, String descricao) {
		super();
		this.codigoModalidade = codigoModalidade;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCodigoModalidade() {
		return codigoModalidade;
	}

	public void setCodigoModalidade(Long codigoModalidade) {
		this.codigoModalidade = codigoModalidade;
	}

}
