package br.com.serratec.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.serratec.ecommerce.models.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer>{

	Optional<Categoria> findByNome(String nome);

}
