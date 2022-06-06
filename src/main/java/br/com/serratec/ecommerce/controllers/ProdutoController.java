package br.com.serratec.ecommerce.controllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.ecommerce.dtos.ProdutoDTO;
import br.com.serratec.ecommerce.exceptions.CategoriaInexistenteException;
import br.com.serratec.ecommerce.exceptions.FuncionarioInexistenteException;
import br.com.serratec.ecommerce.exceptions.ImagemExistenteException;
import br.com.serratec.ecommerce.exceptions.ProdutoExistenteException;
import br.com.serratec.ecommerce.exceptions.ProdutoInexistenteException;
import br.com.serratec.ecommerce.mappers.ImagemMapper;
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
	
	@Autowired
	ImagemMapper imagemMapper;
	
	@PostMapping
	public ResponseEntity<String> createProduto(@Valid @RequestPart ProdutoDTO produtoDTO, @RequestParam MultipartFile file) throws ProdutoExistenteException, CategoriaInexistenteException, FuncionarioInexistenteException, IOException, ImagemExistenteException {
		Produto produto = produtoMapper.produtoDtoToProduto(produtoDTO);
		produto.setImagem(imagemMapper.multipartFileToImagem(file));
		produtoService.inserir(produto);
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
	public void updateProduto(@PathVariable Integer id, @Valid @RequestBody ProdutoDTO atualizacaoDTO) throws ProdutoInexistenteException, ProdutoExistenteException, CategoriaInexistenteException, FuncionarioInexistenteException {
		produtoService.atualizar(produtoMapper.produtoDtoToProduto(atualizacaoDTO), id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduto(@PathVariable Integer id) throws ProdutoInexistenteException {
		produtoService.deletar(id);
	}
}
