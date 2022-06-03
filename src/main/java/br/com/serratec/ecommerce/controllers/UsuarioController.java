package br.com.serratec.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.serratec.ecommerce.exceptions.UsuarioExistenteException;
import br.com.serratec.ecommerce.exceptions.UsuarioInexistenteException;
import br.com.serratec.ecommerce.models.Usuario;
import br.com.serratec.ecommerce.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping
	public void createUsuario(@RequestBody Usuario usuario) throws UsuarioExistenteException {
		usuarioService.inserir(usuario);
	}
	
	@GetMapping
	public List<Usuario> readUsuarios() {
		return usuarioService.listarTudo();
	}
	
	@GetMapping("/{id}")
	public Usuario readUsuario(@PathVariable Integer id) throws UsuarioInexistenteException {
		return usuarioService.listar(id);
	}
	
	@PutMapping("/{id}")
	public void updateUsuario(@PathVariable Integer id, @RequestBody Usuario atualizacao) throws UsuarioInexistenteException, UsuarioExistenteException {
		usuarioService.atualizar(atualizacao, id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteUsuario(@PathVariable Integer id) throws UsuarioInexistenteException {
		usuarioService.deletar(id);
	}
}
