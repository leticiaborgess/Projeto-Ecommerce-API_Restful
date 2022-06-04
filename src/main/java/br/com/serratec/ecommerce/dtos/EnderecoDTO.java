package br.com.serratec.ecommerce.dtos;

public class EnderecoDTO {
	private String cep;
	private String numero;
	private String complemento;
	private Integer clienteId;
	
	
	public EnderecoDTO() {
		super();
	}


	public EnderecoDTO(String cep, String numero, String complemento, Integer clienteId) {
		super();
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.clienteId = clienteId;
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


	public Integer getClienteId() {
		return clienteId;
	}


	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}
}
