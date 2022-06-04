package br.com.serratec.ecommerce.security;

import br.com.serratec.ecommerce.models.Usuario;
import br.com.serratec.ecommerce.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = service.getUsuario(username);
        return new UserSS(usuario.getId(), usuario.getEmail(), usuario.getSenha());
    }
}
