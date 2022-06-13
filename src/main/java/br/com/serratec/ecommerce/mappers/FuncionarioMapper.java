package br.com.serratec.ecommerce.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.serratec.ecommerce.dtos.FuncionarioDTO;
import br.com.serratec.ecommerce.dtos.FuncionarioOutDTO;
import br.com.serratec.ecommerce.models.Funcionario;
import br.com.serratec.ecommerce.models.Usuario;

@Component
public class FuncionarioMapper {
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	public Funcionario funcionarioDtoToFuncionario(FuncionarioDTO funcionarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setEmail(funcionarioDTO.getEmail());
		usuario.setUsername(funcionarioDTO.getUsername());
		usuario.setRole("funcionario");
		
		if(funcionarioDTO.getSenha() != null) {
			usuario.setSenha(bCryptPasswordEncoder.encode(funcionarioDTO.getSenha()));
		}
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome(funcionarioDTO.getNome());
		funcionario.setCpf(funcionarioDTO.getCpf());
		funcionario.setTelefone(funcionarioDTO.getTelefone());
		funcionario.setDataNascimento(funcionarioDTO.getDataNascimento());
		funcionario.setUsuario(usuario);
		
		return funcionario;
	}
	
	public FuncionarioOutDTO funcionarioToFuncionarioOutDto(Funcionario funcionario) {
		FuncionarioOutDTO funcionarioDTO = new FuncionarioOutDTO();
		
		funcionarioDTO.setId(funcionario.getId());
		funcionarioDTO.setCpf(funcionario.getCpf());
		funcionarioDTO.setEmail(funcionario.getUsuario().getEmail());
		funcionarioDTO.setUsername(funcionario.getUsuario().getUsername());
		
		return funcionarioDTO;
	}
}
