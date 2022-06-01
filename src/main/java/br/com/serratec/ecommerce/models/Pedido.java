package br.com.serratec.ecommerce.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String numero;
	
	@NotNull
	private Double valorTotal;
	
	@NotNull
	private LocalDate data;
	
	@NotNull
	private LocalDate dataEntrega;
	
	@NotNull
	private String status;
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany(mappedBy = "pedido")
	private List<ProdutoPedido> produtosPedidos;
	
	
	public Pedido() {
		super();
	}

	public Pedido(Integer id, @NotNull String numero, @NotNull Double valorTotal, @NotNull LocalDate data,
			@NotNull LocalDate dataEntrega, @NotNull String status, Cliente cliente,
			List<ProdutoPedido> produtosPedidos) {
		super();
		this.id = id;
		this.numero = numero;
		this.valorTotal = valorTotal;
		this.data = data;
		this.dataEntrega = dataEntrega;
		this.status = status;
		this.cliente = cliente;
		this.produtosPedidos = produtosPedidos;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ProdutoPedido> getProdutosPedidos() {
		return produtosPedidos;
	}

	public void setProdutosPedidos(List<ProdutoPedido> produtosPedidos) {
		this.produtosPedidos = produtosPedidos;
	}
}
