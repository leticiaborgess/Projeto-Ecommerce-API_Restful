package br.com.serratec.ecommerce.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Imagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	private String mimeType;
	
	@Lob
	private byte[] data;
	 
	@OneToOne(mappedBy = "imagem")
	@JsonIgnore
	private Produto produto;
	
	
	public Imagem() {
		super();
	}

	public Imagem(Integer id, String nome, String mimeType, byte[] data, Produto produto) {
		super();
		this.id = id;
		this.nome = nome;
		this.mimeType = mimeType;
		this.data = data;
		this.produto = produto;
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

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
