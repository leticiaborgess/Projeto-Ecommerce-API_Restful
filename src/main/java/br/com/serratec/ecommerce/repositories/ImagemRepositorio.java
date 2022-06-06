package br.com.serratec.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.ecommerce.models.Imagem;

public interface ImagemRepositorio extends JpaRepository<Imagem, Integer>{

	Optional<Imagem> findByNome(String nome);

}
