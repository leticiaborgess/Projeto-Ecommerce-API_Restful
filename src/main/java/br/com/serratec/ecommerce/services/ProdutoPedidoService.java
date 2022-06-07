package br.com.serratec.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.exceptions.ProdutoInexistenteException;
import br.com.serratec.ecommerce.models.ProdutoPedido;

@Service
public class ProdutoPedidoService {
	
	@Autowired
	ProdutoService produtoService;
	
	public void verificaQnt(ProdutoPedido produtoPedido) throws ProdutoInexistenteException {
		if(produtoPedido.getQuantidade() > produtoService.listar(produtoPedido.getProduto().getId()).getQntEstoque());
	}
}
