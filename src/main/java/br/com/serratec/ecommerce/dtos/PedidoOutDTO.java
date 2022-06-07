package br.com.serratec.ecommerce.dtos;

public class PedidoOutDTO {
	private Integer id;
	private String numero;
	private Double valorTotal;
	
	
	public PedidoOutDTO() {
		super();
	}
	public PedidoOutDTO(Integer id, String numero, Double valorTotal) {
		super();
		this.id = id;
		this.numero = numero;
		this.valorTotal = valorTotal;
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
}
