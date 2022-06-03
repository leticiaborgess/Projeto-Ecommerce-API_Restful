package br.com.serratec.ecommerce.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String email;
	
	@NotNull
	private String username;
	
	@NotNull
	private String senha;
	
	@NotNull
	private String role;
	
	@OneToOne(mappedBy = "usuario")
	@JsonIgnore
	private Cliente cliente;
	
	@OneToOne(mappedBy = "usuario")
	@JsonIgnore
	private Funcionario funcionario;
	

	public Usuario() {
		super();
	}

	public Usuario(Integer id, @NotNull String email, @NotNull String username, @NotNull String senha,
			@NotNull String role, Cliente cliente, Funcionario funcionario) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.senha = senha;
		this.role = role;
		this.cliente = cliente;
		this.funcionario = funcionario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}
