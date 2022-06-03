package br.com.serratec.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.exceptions.ClienteExistenteException;
import br.com.serratec.ecommerce.exceptions.ClienteInexistenteException;
import br.com.serratec.ecommerce.models.Cliente;
import br.com.serratec.ecommerce.repositories.ClienteRepositorio;


@Service
public class ClienteService {
	@Autowired
	ClienteRepositorio clienteRepositorio;

	public void verificaExiste(String cpf) throws ClienteExistenteException {
		Optional<Cliente> optional = clienteRepositorio.findByCpf(cpf);
		if (optional.isPresent()) {
			throw new ClienteExistenteException();
		}
	}

	public List<Cliente> listarTudo() {
		return clienteRepositorio.findAll();
	}

	public Cliente listar(Integer id) throws ClienteInexistenteException {
		Optional<Cliente> optional = clienteRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new ClienteInexistenteException(); // TODO tratar

		}
		return optional.get();
	}

	public void inserir(Cliente cliente) throws ClienteExistenteException {
		verificaExiste(cliente.getCpf());
		clienteRepositorio.save(cliente);
	}

	public Cliente atualizar(Cliente cliente, Integer id)
			throws ClienteInexistenteException, ClienteExistenteException {
		Optional<Cliente> optional = clienteRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new ClienteInexistenteException();
		}
		Cliente oldCliente = optional.get();
		
		if (cliente.getNome() != null && !cliente.getNome().equals("")) {
			verificaExiste(cliente.getCpf());
			oldCliente.setNome(cliente.getNome());
		}
		if (cliente.getTelefone() != null && !cliente.getTelefone().equals("")) {
			oldCliente.setTelefone(cliente.getTelefone());
		}
		if (cliente.getDataNacimento() != null) {
			oldCliente.setDataNacimento(cliente.getDataNacimento());
		}
		//TODO fazer endereço
		return clienteRepositorio.save(oldCliente);
	}

	public void deletar(Integer id) throws ClienteInexistenteException {
		Optional<Cliente> optional = clienteRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new ClienteInexistenteException();

		}
		clienteRepositorio.deleteById(id);
	}
}
