package br.com.serratec.ecommerce.dtos;

public class EnderecoOutDTO {
	private Integer id;
	private String cep;
	private String numero;
	private String complemento;
	
		
	public EnderecoOutDTO() {
		super();
	}
	public EnderecoOutDTO(Integer id, String cep, String numero, String complemento) {
		super();
		this.id = id;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
