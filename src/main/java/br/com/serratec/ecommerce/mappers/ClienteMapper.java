package br.com.serratec.ecommerce.mappers;

import org.springframework.stereotype.Component;

import br.com.serratec.ecommerce.dtos.ClienteDTO;
import br.com.serratec.ecommerce.dtos.ClienteOutDTO;
import br.com.serratec.ecommerce.models.Cliente;
import br.com.serratec.ecommerce.models.Usuario;

@Component
public class ClienteMapper {
	
	public Cliente clienteDtoToCliente(ClienteDTO clienteDTO) {
		Usuario usuario = new Usuario();
		usuario.setEmail(clienteDTO.getEmail());
		usuario.setUsername(clienteDTO.getUsername());
		usuario.setSenha(clienteDTO.getSenha());
		usuario.setRole("cliente");
		
		Cliente cliente = new Cliente();
		cliente.setNome(clienteDTO.getNome());
		cliente.setCpf(clienteDTO.getCpf());
		cliente.setTelefone(clienteDTO.getTelefone());
		cliente.setDataNascimento(clienteDTO.getDataNascimento());
		cliente.setUsuario(usuario);
		
		return cliente;
	}
	
	public ClienteOutDTO clienteToClienteOutDto(Cliente cliente) {
		ClienteOutDTO clienteDTO = new ClienteOutDTO();
		
		clienteDTO.setId(cliente.getId());
		clienteDTO.setCpf(cliente.getCpf());
		clienteDTO.setEmail(cliente.getUsuario().getEmail());
		clienteDTO.setUsername(cliente.getUsuario().getUsername());
		
		return clienteDTO;
	}
}
