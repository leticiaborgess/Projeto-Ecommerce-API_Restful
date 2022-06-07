package br.com.serratec.ecommerce.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.serratec.ecommerce.dtos.ProdutoDTO;
import br.com.serratec.ecommerce.dtos.ProdutoOutDTO;
import br.com.serratec.ecommerce.dtos.ProdutoOutDetDTO;
import br.com.serratec.ecommerce.exceptions.CategoriaInexistenteException;
import br.com.serratec.ecommerce.exceptions.FuncionarioInexistenteException;
import br.com.serratec.ecommerce.models.Produto;
import br.com.serratec.ecommerce.services.CategoriaService;
import br.com.serratec.ecommerce.services.FuncionarioService;
import br.com.serratec.ecommerce.services.ImagemService;

@Component
public class ProdutoMapper {
	
	@Autowired
	CategoriaService categoriaService;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	ImagemService imagemService;
	
	public Produto produtoDtoToProduto(ProdutoDTO produtoDTO) throws CategoriaInexistenteException, FuncionarioInexistenteException {
		Produto produto = new Produto();
		
		produto.setNome(produtoDTO.getNome());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setPreco(produtoDTO.getPreco());
		produto.setQntEstoque(produtoDTO.getQntEstoque());
		produto.setDataCadastro(produtoDTO.getDataCadastro());
		
		if(produtoDTO.getCategoriaId() != null) {
			produto.setCategoria(categoriaService.listar(produtoDTO.getCategoriaId()));
		}
		if(produtoDTO.getFuncionarioId() != null) {
			produto.setFuncionario(funcionarioService.listar(produtoDTO.getFuncionarioId()));			
		}
		
		return produto;
	}
	
	public ProdutoOutDTO produtoToProdutoOutDto(Produto produto) {
		ProdutoOutDTO produtoDTO = new ProdutoOutDTO();
		
		produtoDTO.setId(produto.getId());
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setPreco(produto.getPreco());
		produtoDTO.setQntEstoque(produto.getQntEstoque());
		produtoDTO.setCategoriaNome(produto.getCategoria().getNome());
		produtoDTO.setImagemUrl(imagemService.geraUrl(produto.getId()));
		
		return produtoDTO;
	}
	
	public ProdutoOutDetDTO produtoToProdutoOutDetDto(Produto produto) {
		ProdutoOutDetDTO produtoDTO = new ProdutoOutDetDTO();
		produtoDTO.setId(produto.getId());
		produtoDTO.setNome(produto.getNome());
		produtoDTO.setDescricao(produto.getDescricao());
		produtoDTO.setPreco(produto.getPreco());
		produtoDTO.setQntEstoque(produto.getQntEstoque());
		produtoDTO.setDataCadastro(produto.getDataCadastro());
		produtoDTO.setImagemUrl(imagemService.geraUrl(produto.getId()));
		produtoDTO.setFuncionarioCpf(produto.getFuncionario().getCpf());
		produtoDTO.setCategoriaNome(produto.getCategoria().getNome());
		
		return produtoDTO;
	}
}
