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

import br.com.serratec.ecommerce.dtos.PedidoDTO;
import br.com.serratec.ecommerce.dtos.PedidoOutDTO;
import br.com.serratec.ecommerce.exceptions.ClienteInexistenteException;
import br.com.serratec.ecommerce.exceptions.PedidoExistenteException;
import br.com.serratec.ecommerce.exceptions.PedidoInexistenteException;
import br.com.serratec.ecommerce.exceptions.ProdutoInexistenteException;
import br.com.serratec.ecommerce.mappers.PedidoMapper;
import br.com.serratec.ecommerce.models.Pedido;
import br.com.serratec.ecommerce.services.PedidoService;

@RestController
@RequestMapping("/pedido")
public class pedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	PedidoMapper pedidoMapper;
	
	@PostMapping
	public ResponseEntity<String> createPedido(@Valid @RequestBody PedidoDTO pedidoDTO) throws PedidoExistenteException, ClienteInexistenteException, ProdutoInexistenteException {
		pedidoService.inserir(pedidoMapper.pedidoDtoToPedido(pedidoDTO));
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<PedidoOutDTO> readPedidos() {
		List<PedidoOutDTO> listaDTO = new ArrayList<>();
		
		for(Pedido pedido : pedidoService.listarTudo()) {
			listaDTO.add(pedidoMapper.pedidoToPedidoOutDto(pedido));
		}
		
		return listaDTO;
	}
	
	@GetMapping("/{id}")
	public Pedido readPedido(@PathVariable Integer id) throws PedidoInexistenteException {
		return pedidoService.listar(id);
	}
	
	@PutMapping("/{id}")
	public void updatePedido(@PathVariable Integer id, @Valid @RequestBody PedidoDTO atualizacaoDTO) throws PedidoInexistenteException, PedidoExistenteException, ClienteInexistenteException, ProdutoInexistenteException {
		pedidoService.atualizar(pedidoMapper.pedidoDtoToPedido(atualizacaoDTO), id);
	}
	
	@DeleteMapping("/{id}")
	public void deletePedido(@PathVariable Integer id) throws PedidoInexistenteException {
		pedidoService.deletar(id);
	}
}
