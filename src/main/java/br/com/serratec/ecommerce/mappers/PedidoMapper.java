package br.com.serratec.ecommerce.mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.serratec.ecommerce.dtos.PedidoDTO;
import br.com.serratec.ecommerce.dtos.PedidoOutDTO;
import br.com.serratec.ecommerce.exceptions.ClienteInexistenteException;
import br.com.serratec.ecommerce.exceptions.ProdutoInexistenteException;
import br.com.serratec.ecommerce.models.Pedido;
import br.com.serratec.ecommerce.models.Produto;
import br.com.serratec.ecommerce.models.ProdutoPedido;
import br.com.serratec.ecommerce.services.ClienteService;
import br.com.serratec.ecommerce.services.ProdutoService;

@Component
public class PedidoMapper {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ProdutoService produtoService;
	
	public Pedido pedidoDtoToPedido(PedidoDTO pedidoDTO) throws ClienteInexistenteException, ProdutoInexistenteException {
		Pedido pedido = new Pedido();
		List<ProdutoPedido> produtosPedidos = new ArrayList<>();
		Double valorTotal = 0.0;
		
		for(List<Integer> lista : pedidoDTO.getPedidos()) {
			ProdutoPedido produtoPedido = new ProdutoPedido();
			Produto produto = produtoService.listar(lista.get(0));
			produtoPedido.setPedido(pedido);
			produtoPedido.setProduto(produto);
			produtoPedido.setQuantidade(lista.get(1));
			produtoPedido.setPreco(produto.getPreco() * lista.get(1));
			
			valorTotal += produtoPedido.getPreco();
			produtosPedidos.add(produtoPedido);
		}
		
		pedido.setNumero(pedidoDTO.getNumero());
		pedido.setDataPedido(LocalDate.now());
		pedido.setDataEntrega(pedidoDTO.getDataEntrega());
		pedido.setStatus(false);
		pedido.setCliente(clienteService.listar(pedidoDTO.getClienteId()));
		pedido.setProdutosPedidos(produtosPedidos);
		pedido.setValorTotal(valorTotal);
		
		return pedido;
	}
	
	public PedidoOutDTO pedidoToPedidoOutDto(Pedido pedido) {
		PedidoOutDTO pedidoDTO = new PedidoOutDTO();
		
		pedidoDTO.setId(pedido.getId());
		pedidoDTO.setNumero(pedido.getNumero());
		pedidoDTO.setValorTotal(pedido.getValorTotal());
		
		return pedidoDTO;
	}
}
