package br.com.serratec.ecommerce.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.serratec.ecommerce.dtos.FuncionarioDTO;
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
}
