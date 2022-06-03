package br.com.serratec.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.ecommerce.models.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer>{

	public Optional<Cliente> findByNome(String nome);

	public Optional<Cliente> findByCpf(String cpf);

}
