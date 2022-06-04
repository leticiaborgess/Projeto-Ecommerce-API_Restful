package br.com.serratec.ecommerce.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.dtos.ProdutoDTO;
import br.com.serratec.ecommerce.exceptions.CategoriaInexistenteException;
import br.com.serratec.ecommerce.exceptions.ProdutoExistenteException;
import br.com.serratec.ecommerce.exceptions.ProdutoInexistenteException;
import br.com.serratec.ecommerce.mappers.ProdutoMapper;
import br.com.serratec.ecommerce.models.Produto;
import br.com.serratec.ecommerce.services.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
	
	@Autowired
	ProdutoService produtoService;
	
	@Autowired
	ProdutoMapper produtoMapper;
	
	@PostMapping
	public ResponseEntity<String> createProduto(@Valid @RequestBody ProdutoDTO produtoDTO) throws ProdutoExistenteException, CategoriaInexistenteException {
		produtoService.inserir(produtoMapper.produtoDtoToProduto(produtoDTO));
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<Produto> readProdutos() {
		return produtoService.listarTudo();
	}
	
	@GetMapping("/{id}")
	public Produto readProduto(@PathVariable Integer id) throws ProdutoInexistenteException {
		return produtoService.listar(id);
	}
	
	@PutMapping("/{id}")
	public void updateProduto(@PathVariable Integer id, @Valid @RequestBody Produto atualizacao) throws ProdutoInexistenteException, ProdutoExistenteException {
		produtoService.atualizar(atualizacao, id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduto(@PathVariable Integer id) throws ProdutoInexistenteException {
		produtoService.deletar(id);
	}
}
