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

import br.com.serratec.ecommerce.dtos.EnderecoDTO;
import br.com.serratec.ecommerce.dtos.EnderecoOutDTO;
import br.com.serratec.ecommerce.exceptions.ClienteInexistenteException;
import br.com.serratec.ecommerce.exceptions.EnderecoExistenteException;
import br.com.serratec.ecommerce.exceptions.EnderecoInexistenteException;
import br.com.serratec.ecommerce.mappers.EnderecoMapper;
import br.com.serratec.ecommerce.models.Endereco;
import br.com.serratec.ecommerce.restClient.RestViaCep;
import br.com.serratec.ecommerce.services.ClienteService;
import br.com.serratec.ecommerce.services.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	EnderecoMapper enderecoMapper;
	
	@Autowired
	RestViaCep restViaCep;
	
	@PostMapping
	public ResponseEntity<String> createEndereco(@Valid @RequestBody EnderecoDTO enderecoDTO) throws EnderecoExistenteException, ClienteInexistenteException {
		Endereco endereco = enderecoMapper.ViaCepDtoToEndereco(restViaCep.getViaCep(enderecoDTO.getCep()));
		endereco.setNumero(enderecoDTO.getNumero());
		endereco.setComplemento(enderecoDTO.getComplemento());
		
		Integer clienteId = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName().split("-")[0]);
		endereco.setCliente(clienteService.listar(clienteId));
		
		enderecoService.inserir(endereco);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<EnderecoOutDTO> readEnderecos() throws NumberFormatException, ClienteInexistenteException {
		List<EnderecoOutDTO> listaDTO = new ArrayList<>();
		
		for(Endereco endereco : enderecoService.listarTudo()) {
			listaDTO.add(enderecoMapper.enderecoToEnderecoOutDto(endereco));
		}
		
		return listaDTO;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Endereco> readEndereco(@PathVariable Integer id) throws EnderecoInexistenteException {
		Endereco endereco = enderecoService.listar(id);
				
		if(!SecurityContextHolder.getContext().getAuthentication().getName().split("-")[0].equals(endereco.getCliente().getId().toString())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<>(endereco, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateEndereco(@PathVariable Integer id, @Valid @RequestBody EnderecoDTO atualizacaoDTO) throws EnderecoInexistenteException, EnderecoExistenteException {
		Endereco endereco = enderecoService.listar(id);
		
		if(!SecurityContextHolder.getContext().getAuthentication().getName().split("-")[0].equals(endereco.getCliente().getId().toString())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		
		Endereco atualizacao = new Endereco();
		
		if(atualizacaoDTO.getCep() != null) {
			atualizacao = enderecoMapper.ViaCepDtoToEndereco(restViaCep.getViaCep(atualizacaoDTO.getCep()));
		}
		
		atualizacao.setNumero(atualizacaoDTO.getNumero());
		atualizacao.setComplemento(atualizacaoDTO.getComplemento());
		
		enderecoService.atualizar(atualizacao, id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEndereco(@PathVariable Integer id) throws EnderecoInexistenteException {
		Endereco endereco = enderecoService.listar(id);

		if(!SecurityContextHolder.getContext().getAuthentication().getName().split("-")[0].equals(endereco.getCliente().getId().toString())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		enderecoService.deletar(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
