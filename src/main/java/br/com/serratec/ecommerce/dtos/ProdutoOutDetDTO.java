package br.com.serratec.ecommerce.dtos;

import java.time.LocalDate;

public class ProdutoOutDetDTO {
	private Integer id;
	private String nome;
	private String descricao;
	private Double preco;
	private Integer qntEstoque;
	private LocalDate dataCadastro;
	private String imagemUrl;
	private String funcionarioCpf;
	private String categoriaNome;
	
	
	public ProdutoOutDetDTO() {
		super();
	}
	public ProdutoOutDetDTO(Integer id, String nome, String descricao, Double preco, Integer qntEstoque,
			LocalDate dataCadastro, String imagemUrl, String funcionarioCpf, String categoriaNome) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.qntEstoque = qntEstoque;
		this.dataCadastro = dataCadastro;
		this.imagemUrl = imagemUrl;
		this.funcionarioCpf = funcionarioCpf;
		this.categoriaNome = categoriaNome;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getImagemUrl() {
		return imagemUrl;
	}
	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}
	public String getFuncionarioCpf() {
		return funcionarioCpf;
	}
	public void setFuncionarioCpf(String funcionarioCpf) {
		this.funcionarioCpf = funcionarioCpf;
	}
	public String getCategoriaNome() {
		return categoriaNome;
	}
	public void setCategoriaNome(String categoriaNome) {
		this.categoriaNome = categoriaNome;
	}
}
