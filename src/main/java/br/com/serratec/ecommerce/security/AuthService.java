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
		return new UserSS(usuario.getUsername(), usuario.getSenha(), usuario.getRole());
	}
}
