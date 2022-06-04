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

import br.com.serratec.ecommerce.dtos.EnderecoDTO;
import br.com.serratec.ecommerce.exceptions.EnderecoExistenteException;
import br.com.serratec.ecommerce.exceptions.EnderecoInexistenteException;
import br.com.serratec.ecommerce.mappers.EnderecoMapper;
import br.com.serratec.ecommerce.models.Endereco;
import br.com.serratec.ecommerce.restClient.RestViaCep;
import br.com.serratec.ecommerce.services.EnderecoService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
	
	@Autowired
	EnderecoService enderecoService;
	
	@Autowired
	EnderecoMapper enderecoMapper;
	
	@Autowired
	RestViaCep restViaCep;
	
	@PostMapping
	public ResponseEntity<String> createEndereco(@Valid @RequestBody EnderecoDTO enderecoDTO) throws EnderecoExistenteException {
		Endereco endereco = enderecoMapper.ViaCepDtoToEndereco(restViaCep.getViaCep(enderecoDTO.getCep()));
		endereco.setNumero(enderecoDTO.getNumero());
		endereco.setComplemento(enderecoDTO.getComplemento());
		
		enderecoService.inserir(endereco);
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<Endereco> readEnderecos() {
		return enderecoService.listarTudo();
	}
	
	@GetMapping("/{id}")
	public Endereco readEndereco(@PathVariable Integer id) throws EnderecoInexistenteException {
		return enderecoService.listar(id);
	}
	
	@PutMapping("/{id}")
	public void updateEndereco(@PathVariable Integer id, @Valid @RequestBody EnderecoDTO atualizacaoDTO) throws EnderecoInexistenteException, EnderecoExistenteException {
		Endereco atualizacao = enderecoMapper.ViaCepDtoToEndereco(restViaCep.getViaCep(atualizacaoDTO.getCep()));
		atualizacao.setNumero(atualizacaoDTO.getNumero());
		atualizacao.setComplemento(atualizacaoDTO.getComplemento());
		
		enderecoService.atualizar(atualizacao, id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteEndereco(@PathVariable Integer id) throws EnderecoInexistenteException {
		enderecoService.deletar(id);
	}
}
