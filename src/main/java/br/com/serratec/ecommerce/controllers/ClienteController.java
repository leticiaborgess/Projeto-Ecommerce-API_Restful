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

import br.com.serratec.ecommerce.dtos.ClienteDTO;
import br.com.serratec.ecommerce.exceptions.ClienteExistenteException;
import br.com.serratec.ecommerce.exceptions.ClienteInexistenteException;
import br.com.serratec.ecommerce.exceptions.UsuarioExistenteException;
import br.com.serratec.ecommerce.mappers.ClienteMapper;
import br.com.serratec.ecommerce.models.Cliente;
import br.com.serratec.ecommerce.services.ClienteService;


@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	ClienteMapper clienteMapper;
	
	@PostMapping
	public ResponseEntity<String> createCliente(@Valid @RequestBody ClienteDTO clienteDTO) throws ClienteExistenteException, UsuarioExistenteException {
		clienteService.inserir(clienteMapper.clienteDtoToCliente(clienteDTO));
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<Cliente> readClientes() {
		return clienteService.listarTudo();
	}
	
	@GetMapping("/{id}")
	public Cliente readCliente(@PathVariable Integer id) throws ClienteInexistenteException {
		return clienteService.listar(id);
	}
	
	@PutMapping("/{id}")
	public void updateCliente(@PathVariable Integer id, @Valid @RequestBody Cliente atualizacao) throws ClienteInexistenteException, ClienteExistenteException {
		clienteService.atualizar(atualizacao, id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCliente(@PathVariable Integer id) throws ClienteInexistenteException {
		clienteService.deletar(id);
	}
}
