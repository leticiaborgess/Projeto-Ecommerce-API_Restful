package br.com.serratec.ecommerce.mappers;

import br.com.serratec.ecommerce.dtos.FuncionarioDTO;
import br.com.serratec.ecommerce.models.Funcionario;
import br.com.serratec.ecommerce.models.Usuario;

public class FuncionarioMapper {
	
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
