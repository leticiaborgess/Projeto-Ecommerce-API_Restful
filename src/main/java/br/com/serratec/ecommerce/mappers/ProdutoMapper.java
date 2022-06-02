package br.com.serratec.ecommerce.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.serratec.ecommerce.dtos.ProdutoDTO;
import br.com.serratec.ecommerce.exceptions.CategoriaInexistenteException;
import br.com.serratec.ecommerce.models.Produto;
import br.com.serratec.ecommerce.services.CategoriaService;

@Component
public class ProdutoMapper {
	
	@Autowired
	CategoriaService categoriaService;
	
	public Produto produtoDtoToProduto(ProdutoDTO produtoDTO) throws CategoriaInexistenteException {
		Produto produto = new Produto();
		
		produto.setNome(produtoDTO.getNome());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setPreco(produtoDTO.getPreco());
		produto.setQntEstoque(produtoDTO.getQntEstoque());
		produto.setDataCadastro(produtoDTO.getDataCadastro());
		produto.setCategoria(categoriaService.listar(produtoDTO.getCategoriaId()));
		//TODO colocar funcionário
		
		return produto;
	}
}
