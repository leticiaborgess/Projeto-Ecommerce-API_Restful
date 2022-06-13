package br.com.serratec.ecommerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.ecommerce.models.Cliente;
import br.com.serratec.ecommerce.models.Endereco;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Integer>{

	List<Endereco> findByCliente(Cliente cliente);

}
