package br.com.serratec.ecommerce.controllers;

import java.util.ArrayList;
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
import br.com.serratec.ecommerce.dtos.ClienteOutDTO;
import br.com.serratec.ecommerce.exceptions.ClienteExistenteException;
import br.com.serratec.ecommerce.exceptions.ClienteInexistenteException;
import br.com.serratec.ecommerce.exceptions.UsuarioExistenteException;
import br.com.serratec.ecommerce.exceptions.UsuarioInexistenteException;
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
	public List<ClienteOutDTO> readClientes() {
		List<ClienteOutDTO> listaDTO = new ArrayList<>();
		
		for(Cliente cliente : clienteService.listarTudo()) {
			listaDTO.add(clienteMapper.clienteToClienteOutDto(cliente));
		}
		
		return listaDTO;
	}
	
	@GetMapping("/{id}")
	public Cliente readCliente(@PathVariable Integer id) throws ClienteInexistenteException {
		return clienteService.listar(id);
	}
	
	@PutMapping("/{id}")
	public void updateCliente(@PathVariable Integer id, @Valid @RequestBody ClienteDTO atualizacaoDTO) throws ClienteInexistenteException, ClienteExistenteException, UsuarioInexistenteException, UsuarioExistenteException {
		clienteService.atualizar(clienteMapper.clienteDtoToCliente(atualizacaoDTO), id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCliente(@PathVariable Integer id) throws ClienteInexistenteException {
		clienteService.deletar(id);
	}
}
