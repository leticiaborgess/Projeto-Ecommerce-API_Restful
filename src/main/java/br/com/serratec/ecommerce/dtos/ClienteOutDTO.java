package br.com.serratec.ecommerce.dtos;

public class ClienteOutDTO {
	private Integer id;
	private String cpf;
	private String username;
	private String email;
	
	
	public ClienteOutDTO() {
		super();
	}
	
	public ClienteOutDTO(Integer id, String cpf, String username, String email) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.username = username;
		this.email = email;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
