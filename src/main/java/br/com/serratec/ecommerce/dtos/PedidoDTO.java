package br.com.serratec.ecommerce.dtos;

import java.time.LocalDate;
import java.util.List;

public class PedidoDTO {
	private String numero;
	private LocalDate dataEntrega;
	private Integer clienteId;
	private List<List<Integer>> pedidos;
	
	
	public PedidoDTO() {
		super();
	}
	
	public PedidoDTO(String numero, LocalDate dataEntrega, Integer clienteId, List<List<Integer>> pedidos) {
		super();
		this.numero = numero;
		this.dataEntrega = dataEntrega;
		this.clienteId = clienteId;
		this.pedidos = pedidos;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public LocalDate getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public Integer getClienteId() {
		return clienteId;
	}
	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}
	public List<List<Integer>> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<List<Integer>> pedidos) {
		this.pedidos = pedidos;
	}
}
