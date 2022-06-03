package br.com.serratec.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.serratec.ecommerce.models.Endereco;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Integer>{

}
