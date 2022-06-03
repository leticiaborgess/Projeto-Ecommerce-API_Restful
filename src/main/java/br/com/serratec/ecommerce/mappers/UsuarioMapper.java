package br.com.serratec.ecommerce.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.serratec.ecommerce.dtos.ClienteDTO;
import br.com.serratec.ecommerce.dtos.FuncionarioDTO;
import br.com.serratec.ecommerce.models.Cliente;
import br.com.serratec.ecommerce.models.Funcionario;
import br.com.serratec.ecommerce.models.Usuario;
import br.com.serratec.ecommerce.services.UsuarioService;

@Component
public class UsuarioMapper {
	
	@Autowired
	UsuarioService usuarioService;
	
	
	public Funcionario funcionarioDtoToFuncionario(FuncionarioDTO funcionarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setEmail(funcionarioDTO.getEmail());
		usuario.setUsername(funcionarioDTO.getUsername());
		usuario.setSenha(funcionarioDTO.getSenha());
		usuario.setRole("funcionario");
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(funcionarioDTO.getNome());
		funcionario.setCpf(funcionarioDTO.getCpf());
		funcionario.setTelefone(funcionarioDTO.getTelefone());
		funcionario.setDataNascimento(funcionarioDTO.getDataNascimento());
		funcionario.setUsuario(usuario);
		
		return funcionario;
	}
	
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
}
