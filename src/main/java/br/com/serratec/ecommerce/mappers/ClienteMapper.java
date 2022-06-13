package br.com.serratec.ecommerce.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.serratec.ecommerce.dtos.ClienteDTO;
import br.com.serratec.ecommerce.dtos.ClienteOutDTO;
import br.com.serratec.ecommerce.models.Cliente;
import br.com.serratec.ecommerce.models.Usuario;

@Component
public class ClienteMapper {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public Cliente clienteDtoToCliente(ClienteDTO clienteDTO) {
		Usuario usuario = new Usuario();
		usuario.setEmail(clienteDTO.getEmail());
		usuario.setUsername(clienteDTO.getUsername());
		usuario.setRole("cliente");
		
		if(clienteDTO.getSenha() != null) {			
			usuario.setSenha(bCryptPasswordEncoder.encode(clienteDTO.getSenha()));
		}
		
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
