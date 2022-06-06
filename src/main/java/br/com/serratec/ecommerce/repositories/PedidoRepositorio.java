package br.com.serratec.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.ecommerce.models.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Integer>{

	Optional<Pedido> findByNumero(String numero);

}
