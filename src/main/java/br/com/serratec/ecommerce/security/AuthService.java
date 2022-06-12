package br.com.serratec.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.models.Usuario;
import br.com.serratec.ecommerce.services.UsuarioService;

@Service
public class AuthService implements UserDetailsService{
	
	@Autowired
	UsuarioService usuarioService;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioService.getByUsername(username);
		
		if(usuario.getRole().equals("cliente")) {
			return new UserSS(String.format("%s-%s-%s", usuario.getCliente().getId(), usuario.getUsername(), usuario.getRole()), usuario.getSenha());
		}
		if(usuario.getRole().equals("funcionario")) {
			return new UserSS(String.format("%s-%s-%s", usuario.getFuncionario().getId(), usuario.getUsername(), usuario.getRole()), usuario.getSenha());
		}
		
		return null;
	}
}
