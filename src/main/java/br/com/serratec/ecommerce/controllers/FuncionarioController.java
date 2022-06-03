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

import br.com.serratec.ecommerce.exceptions.FuncionarioExistenteException;
import br.com.serratec.ecommerce.exceptions.FuncionarioInexistenteException;
import br.com.serratec.ecommerce.models.Funcionario;
import br.com.serratec.ecommerce.services.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@PostMapping
	public void createFuncionario(@RequestBody Funcionario funcionario) throws FuncionarioExistenteException {
		funcionarioService.inserir(funcionario);
	}
	
	@GetMapping
	public List<Funcionario> readFuncionarios() {
		return funcionarioService.listarTudo();
	}
	
	@GetMapping("/{id}")
	public Funcionario readFuncionario(@PathVariable Integer id) throws FuncionarioInexistenteException {
		return funcionarioService.listar(id);
	}
	
	@PutMapping("/{id}")
	public void updateFuncionario(@PathVariable Integer id, @RequestBody Funcionario atualizacao) throws FuncionarioInexistenteException, FuncionarioExistenteException {
		funcionarioService.atualizar(atualizacao, id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteFuncionario(@PathVariable Integer id) throws FuncionarioInexistenteException {
		funcionarioService.deletar(id);
	}
}
