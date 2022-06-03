package br.com.serratec.ecommerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.ecommerce.models.Funcionario;

public interface FuncionarioRepositorio extends JpaRepository<Funcionario, Integer>{

	Optional<Funcionario> findByCpf(String cpf);

}
