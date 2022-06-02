package br.com.serratec.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.serratec.ecommerce.models.Produto;

public interface ProdutoRepositorio extends JpaRepository<Produto, Integer>{

	public Optional<Produto> findByNome(String nome);

}
