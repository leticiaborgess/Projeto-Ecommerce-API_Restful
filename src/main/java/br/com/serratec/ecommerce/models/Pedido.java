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
	private String numeroPedido;
	
	@NotNull
	private List<Produto> listaProdutos;
	
	@NotNull
	private Double valorTotal;
	
	@NotNull
	private LocalDate dataPedido;
	
	@NotNull
	private LocalDate dataEntrega;
	
	@NotNull
	private String status;
	
	@NotNull
	@ManyToOne
	private Cliente cliente;
	
	
}
