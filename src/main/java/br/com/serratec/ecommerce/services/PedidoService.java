package br.com.serratec.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.serratec.ecommerce.exceptions.ImagemExistenteException;
import br.com.serratec.ecommerce.exceptions.ImagemInexistenteException;
import br.com.serratec.ecommerce.exceptions.PedidoExistenteException;
import br.com.serratec.ecommerce.exceptions.PedidoInexistenteException;
import br.com.serratec.ecommerce.exceptions.ProdutoExistenteException;
import br.com.serratec.ecommerce.exceptions.ProdutoInexistenteException;
import br.com.serratec.ecommerce.exceptions.QntEstoqueInsuficienteException;
import br.com.serratec.ecommerce.models.Pedido;
import br.com.serratec.ecommerce.models.Produto;
import br.com.serratec.ecommerce.models.ProdutoPedido;
import br.com.serratec.ecommerce.repositories.PedidoRepositorio;

@Service
public class PedidoService {
	@Autowired
	PedidoRepositorio pedidoRepositorio;
	
	@Autowired
	ProdutoService produtoService;
	

	public void verificaExiste(String numero) throws PedidoExistenteException {
		Optional<Pedido> optional = pedidoRepositorio.findByNumero(numero);
		if (optional.isPresent()) {
			throw new PedidoExistenteException();
		}
	}

	public List<Pedido> listarTudo() {
		return pedidoRepositorio.findAll();
	}

	public Pedido listar(Integer id) throws PedidoInexistenteException {
		Optional<Pedido> optional = pedidoRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new PedidoInexistenteException();

		}
		return optional.get();
	}

	@Transactional
	public void inserir(Pedido pedido) throws PedidoExistenteException, ProdutoInexistenteException, QntEstoqueInsuficienteException, ProdutoExistenteException, ImagemInexistenteException, ImagemExistenteException {
		verificaExiste(pedido.getNumero());
		
		for(ProdutoPedido produtoPedido : pedido.getProdutosPedidos()) {
			Produto produto = produtoService.listar(produtoPedido.getProduto().getId());
			
			if(produtoPedido.getQuantidade() > produto.getQntEstoque()) {
				throw new QntEstoqueInsuficienteException();
			}
			
			Produto atualizacao = new Produto();
			atualizacao.setQntEstoque(produto.getQntEstoque() - produtoPedido.getQuantidade());
			produtoService.atualizar(atualizacao, produto.getId());
		}
		
		pedidoRepositorio.save(pedido);
	}

	public Pedido atualizar(Pedido pedido, Integer id)
			throws PedidoInexistenteException, PedidoExistenteException {
		Optional<Pedido> optional = pedidoRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new PedidoInexistenteException();
		}
		Pedido oldPedido = optional.get();
		if (pedido.getDataEntrega() != null) {
			oldPedido.setDataEntrega(pedido.getDataEntrega());
		}
		if (pedido.getStatus()) {
			oldPedido.setStatus(pedido.getStatus());
		}
			
		return pedidoRepositorio.save(oldPedido);
	}

	public void deletar(Integer id) throws PedidoInexistenteException {
		Optional<Pedido> optional = pedidoRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new PedidoInexistenteException();

		}
		pedidoRepositorio.deleteById(id);
	}
	
}
