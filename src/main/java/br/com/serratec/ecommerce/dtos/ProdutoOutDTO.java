package br.com.serratec.ecommerce.dtos;

public class ProdutoOutDTO {
	private Integer id;
	private String nome;
	private Double preco;
	private Integer qntEstoque;
	private String categoriaNome;
	
	
	public ProdutoOutDTO() {
		super();
	}


	public ProdutoOutDTO(Integer id, String nome, Double preco, Integer qntEstoque, String categoriaNome) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.qntEstoque = qntEstoque;
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


	public String getCategoriaNome() {
		return categoriaNome;
	}


	public void setCategoriaNome(String categoriaNome) {
		this.categoriaNome = categoriaNome;
	}
}
