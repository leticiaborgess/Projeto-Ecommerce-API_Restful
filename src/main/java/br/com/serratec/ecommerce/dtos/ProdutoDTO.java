package br.com.serratec.ecommerce.dtos;

import java.time.LocalDate;

public class ProdutoDTO {
	private String nome;
	private String descricao;
	private Double preco;
	private Integer qntEstoque;
	private LocalDate dataCadastro;
	private Integer funcionarioId;
	private Integer categoriaId;
	
	
	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(String nome, String descricao, Double preco, Integer qntEstoque, LocalDate dataCadastro,
			Integer funcionarioId, Integer categoriaId) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.qntEstoque = qntEstoque;
		this.dataCadastro = dataCadastro;
		this.funcionarioId = funcionarioId;
		this.categoriaId = categoriaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQntEstoque() {
		return qntEstoque;
	}

	public void setQntEstoque(Integer qntEstoque) {
		this.qntEstoque = qntEstoque;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Integer getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(Integer funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}
}
