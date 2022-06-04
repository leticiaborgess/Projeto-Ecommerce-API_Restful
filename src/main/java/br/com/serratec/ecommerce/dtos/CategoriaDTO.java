package br.com.serratec.ecommerce.dtos;

public class CategoriaDTO {
	String nome;
	String descricao;
	
	
	public CategoriaDTO() {
		super();
	}
	
	public CategoriaDTO(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
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
}
