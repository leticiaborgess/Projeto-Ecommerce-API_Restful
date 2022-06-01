package br.com.serratec.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.exceptions.CategoriaExistenteException;
import br.com.serratec.ecommerce.exceptions.CategoriaInexistenteException;
import br.com.serratec.ecommerce.models.Categoria;
import br.com.serratec.ecommerce.services.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@PostMapping
	public void createCategoria(@RequestBody Categoria categoria) throws CategoriaExistenteException {
		categoriaService.inserir(categoria);
	}
	
	@GetMapping
	public List<Categoria> readCategorias() {
		return categoriaService.listarTudo();
	}
	
	@GetMapping("/{id}")
	public Categoria readCategoria(@PathVariable Integer id) throws CategoriaInexistenteException {
		return categoriaService.listar(id);
	}
	
	@PutMapping("/{id}")
	public void updateCategoria(@PathVariable Integer id, @RequestBody Categoria catAtualizacao) throws CategoriaInexistenteException, CategoriaExistenteException {
		categoriaService.atualizar(catAtualizacao, id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable Integer id) throws CategoriaInexistenteException {
		categoriaService.deletar(id);
	}
}
