package br.com.serratec.ecommerce.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String nome;
	
	private String descricao;
	
	@NotNull
	private Double preco;
	
	@NotNull
	private Integer qntEstoque;
	
	@NotNull
	private LocalDate dataCadastro;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Imagem imagem;
	
	@OneToMany(mappedBy = "produto")
	@JsonIgnore
	private List<ProdutoPedido> produtosPedidos;
	
	@ManyToOne
	private Funcionario funcionario;
	
	@ManyToOne
	private Categoria categoria;
	
	
	public Produto() {
		super();
	}


	public Produto(Integer id, @NotNull String nome, String descricao, @NotNull Double preco,
			@NotNull Integer qntEstoque, @NotNull @Past LocalDate dataCadastro, Imagem imagem,
			List<ProdutoPedido> produtosPedidos, Funcionario funcionario, Categoria categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.qntEstoque = qntEstoque;
		this.dataCadastro = dataCadastro;
		this.imagem = imagem;
		this.produtosPedidos = produtosPedidos;
		this.funcionario = funcionario;
		this.categoria = categoria;
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


	public Imagem getImagem() {
		return imagem;
	}


	public void setImagem(Imagem imagem) {
		this.imagem = imagem;
	}


	public List<ProdutoPedido> getProdutosPedidos() {
		return produtosPedidos;
	}


	public void setProdutosPedidos(List<ProdutoPedido> produtosPedidos) {
		this.produtosPedidos = produtosPedidos;
	}


	public Funcionario getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}


	public Categoria getCategoria() {
		return categoria;
	}


	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
