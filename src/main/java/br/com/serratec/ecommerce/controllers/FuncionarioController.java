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

import br.com.serratec.ecommerce.dtos.FuncionarioDTO;
import br.com.serratec.ecommerce.exceptions.FuncionarioExistenteException;
import br.com.serratec.ecommerce.exceptions.FuncionarioInexistenteException;
import br.com.serratec.ecommerce.exceptions.UsuarioExistenteException;
import br.com.serratec.ecommerce.exceptions.UsuarioInexistenteException;
import br.com.serratec.ecommerce.mappers.FuncionarioMapper;
import br.com.serratec.ecommerce.models.Funcionario;
import br.com.serratec.ecommerce.services.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	FuncionarioMapper funcionarioMapper;
	
	@PostMapping
	public ResponseEntity<String> createFuncionario(@Valid @RequestBody FuncionarioDTO funcionarioDTO) throws FuncionarioExistenteException, UsuarioExistenteException {
		funcionarioService.inserir(funcionarioMapper.funcionarioDtoToFuncionario(funcionarioDTO));
		return new ResponseEntity<String>(HttpStatus.CREATED);
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
	public void updateFuncionario(@PathVariable Integer id, @Valid @RequestBody FuncionarioDTO atualizacaoDTO) throws FuncionarioInexistenteException, FuncionarioExistenteException, UsuarioInexistenteException, UsuarioExistenteException {
		funcionarioService.atualizar(funcionarioMapper.funcionarioDtoToFuncionario(atualizacaoDTO), id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteFuncionario(@PathVariable Integer id) throws FuncionarioInexistenteException {
		funcionarioService.deletar(id);
	}
}
