package br.com.serratec.ecommerce.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String cpf;
	
	private String telefone;
	
	private LocalDate dataNacimento;
	
	@OneToOne
	private Usuario usuario;
	
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> enderecos;
	
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;
	
	
	public Cliente() {
		super();
	}

	public Cliente(Integer id, @NotNull String nome, @NotNull String cpf, String telefone, LocalDate dataNacimento,
			Usuario usuario, List<Endereco> enderecos, List<Pedido> pedidos) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNacimento = dataNacimento;
		this.usuario = usuario;
		this.enderecos = enderecos;
		this.pedidos = pedidos;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getDataNacimento() {
		return dataNacimento;
	}

	public void setDataNacimento(LocalDate dataNacimento) {
		this.dataNacimento = dataNacimento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
}