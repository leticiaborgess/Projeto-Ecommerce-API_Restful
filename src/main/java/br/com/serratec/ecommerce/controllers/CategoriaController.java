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
	public ResponseEntity<String> createCategoria(@Valid @RequestBody Categoria categoria) throws CategoriaExistenteException {
		categoriaService.inserir(categoria);
		return new ResponseEntity<String>(HttpStatus.CREATED);
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
	public void updateCategoria(@PathVariable Integer id, @Valid @RequestBody Categoria atualizacao) throws CategoriaInexistenteException, CategoriaExistenteException {
		categoriaService.atualizar(atualizacao, id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCategoria(@PathVariable Integer id) throws CategoriaInexistenteException {
		categoriaService.deletar(id);
	}
}
