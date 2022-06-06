package br.com.serratec.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.exceptions.PedidoExistenteException;
import br.com.serratec.ecommerce.exceptions.PedidoInexistenteException;
import br.com.serratec.ecommerce.models.Pedido;
import br.com.serratec.ecommerce.repositories.PedidoRepositorio;

@Service
public class PedidoService {
	@Autowired
	PedidoRepositorio pedidoRepositorio;

	public void verificaExiste(String numero) throws PedidoExistenteException {
		Optional<Pedido> optional = pedidoRepositorio.findByNumero(numero);
		if (optional.isPresent()) {
			throw new PedidoExistenteException();
		}
	}

	public List<Pedido> listarTudo() {
		return pedidoRepositorio.findAll();
	}

	public Pedido listar(Integer id) throws PedidoInexistenteException {
		Optional<Pedido> optional = pedidoRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new PedidoInexistenteException(); // TODO tratar

		}
		return optional.get();
	}

	public void inserir(Pedido pedido) throws PedidoExistenteException {
		verificaExiste(pedido.getNumero());
		pedidoRepositorio.save(pedido);
		// TODO com imagem
	}

	public Pedido atualizar(Pedido pedido, Integer id)
			throws PedidoInexistenteException, PedidoExistenteException {
		Optional<Pedido> optional = pedidoRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new PedidoInexistenteException();
		}
		Pedido oldPedido = optional.get();
		if (pedido.getDataEntrega() != null) {
			oldPedido.setDataEntrega(pedido.getDataEntrega());
		}
		if (pedido.getStatus()) {
			oldPedido.setStatus(pedido.getStatus());
		}
			
		return pedidoRepositorio.save(oldPedido);
	}

	public void deletar(Integer id) throws PedidoInexistenteException {
		Optional<Pedido> optional = pedidoRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new PedidoInexistenteException();

		}
		pedidoRepositorio.deleteById(id);
	}
	
}
