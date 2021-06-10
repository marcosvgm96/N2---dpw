package br.com.academia.model;

public class ClienteCompleto  extends Cliente{
	private Long id;
	private String nome;
	private String matricula;
	private Endereco endereco;
	private Modalidade modalidade;

	public ClienteCompleto(Long id, String nome, String matricula, Endereco endereco, Modalidade modalidade) {
		this.id = id;
		this.nome = nome;
		this.matricula = matricula;
		this.endereco = endereco;
		this.modalidade = modalidade;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getMatricula() {
		return matricula;
	}

	@Override
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Modalidade getModalidade() {
		return modalidade;
	}

	public void setModalidade(Modalidade modalidade) {
		this.modalidade = modalidade;
	}

}
