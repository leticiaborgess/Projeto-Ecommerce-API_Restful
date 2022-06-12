package br.com.serratec.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.exceptions.UsuarioExistenteException;
import br.com.serratec.ecommerce.exceptions.UsuarioInexistenteException;
import br.com.serratec.ecommerce.models.Usuario;
import br.com.serratec.ecommerce.repositories.UsuarioRepositorio;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepositorio usuarioRepositorio;

	
	public void verificaExiste(String username) throws UsuarioExistenteException {
		Optional<Usuario> optional = usuarioRepositorio.findByUsername(username);
		if (optional.isPresent()) {
			throw new UsuarioExistenteException();
		}
	}

	public List<Usuario> listarTudo() {
		return usuarioRepositorio.findAll();
	}

	public Usuario listar(Integer id) throws UsuarioInexistenteException {
		Optional<Usuario> optional = usuarioRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new UsuarioInexistenteException();

		}
		return optional.get();
	}
	
	public Usuario getByUsername(String username) {
		Optional<Usuario> optional = usuarioRepositorio.findByUsername(username);
		if (optional.isEmpty()) {
			throw new UsernameNotFoundException("Usuario não encontrado");

		}
		return optional.get();
	}

	public void inserir(Usuario usuario) throws UsuarioExistenteException {
		verificaExiste(usuario.getUsername());
		usuarioRepositorio.save(usuario);
	}

	public Usuario atualizar(Usuario usuario, Integer id) throws UsuarioInexistenteException, UsuarioExistenteException {
        Optional<Usuario> optional = usuarioRepositorio.findById(id);
        if (optional.isEmpty()) {
            throw new UsuarioInexistenteException();
        }
        Usuario oldUsuario = optional.get();
        if (usuario.getUsername() != null && !usuario.getUsername().equals("")) {
            verificaExiste(usuario.getUsername());
            oldUsuario.setUsername(usuario.getUsername());
        }
        if (usuario.getEmail() != null && !usuario.getEmail().equals("")) {
            oldUsuario.setEmail(usuario.getEmail());
        }
        if (usuario.getSenha() != null && !usuario.getSenha().equals("")) {
            oldUsuario.setSenha(usuario.getSenha());
        }
        return usuarioRepositorio.save(oldUsuario);
	}

	public void deletar(Integer id) throws UsuarioInexistenteException {
		Optional<Usuario> optional = usuarioRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new UsuarioInexistenteException();
        
		}
		usuarioRepositorio.deleteById(id);
	}

	public Usuario getUsuario(String email){
		Optional<Usuario> optional = usuarioRepositorio.findByEmail(email);
		if(optional.isEmpty()){
			return null;
		}
		return optional.get();
	}
}
