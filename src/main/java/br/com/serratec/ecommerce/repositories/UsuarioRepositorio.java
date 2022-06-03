package br.com.serratec.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.ecommerce.models.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByUsername(String username);

}
