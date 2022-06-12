package br.com.serratec.ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
	public ResponseEntity<Cliente> readCliente(@PathVariable Integer id) throws ClienteInexistenteException {
		if(!SecurityContextHolder.getContext().getAuthentication().getName().split("-")[0].equals(id.toString())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(clienteService.listar(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateCliente(@PathVariable Integer id, @Valid @RequestBody ClienteDTO atualizacaoDTO) throws ClienteInexistenteException, ClienteExistenteException, UsuarioInexistenteException, UsuarioExistenteException {
		if(!SecurityContextHolder.getContext().getAuthentication().getName().split("-")[0].equals(id.toString())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		clienteService.atualizar(clienteMapper.clienteDtoToCliente(atualizacaoDTO), id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCliente(@PathVariable Integer id) throws ClienteInexistenteException {
		if(!SecurityContextHolder.getContext().getAuthentication().getName().split("-")[0].equals(id.toString())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		clienteService.deletar(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
}
