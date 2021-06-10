package br.com.academia.model;

public class Cliente {

	private Long id;
	private String nome;
	private String matricula;
	private Long enderecoId;

	private Long modalidadeId;

	public Cliente() {
		super();
	}

	public Cliente(Long id, String nome, String matricula, Long endereco, Long modalidade) {
		super();
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
		this.enderecoId = endereco;
		this.modalidadeId = modalidade;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Long getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(Long enderecoId) {
		this.enderecoId = enderecoId;
	}

	public Long getModalidadeId() {
		return modalidadeId;
	}

	public void setModalidadeId(Long modalidadeId) {
		this.modalidadeId = modalidadeId;
	}

}
