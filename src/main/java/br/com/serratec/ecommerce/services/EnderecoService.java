package br.com.serratec.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.exceptions.ClienteInexistenteException;
import br.com.serratec.ecommerce.exceptions.EnderecoExistenteException;
import br.com.serratec.ecommerce.exceptions.EnderecoInexistenteException;
import br.com.serratec.ecommerce.models.Cliente;
import br.com.serratec.ecommerce.models.Endereco;
import br.com.serratec.ecommerce.repositories.EnderecoRepositorio;

@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepositorio enderecoRepositorio;
	
	@Autowired
	ClienteService clienteService;

	public List<Endereco> listarTudo() throws NumberFormatException, ClienteInexistenteException {
		Cliente cliente = clienteService.listar(Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName().split("-")[0]));
		return enderecoRepositorio.findByCliente(cliente);
	}

	public Endereco listar(Integer id) throws EnderecoInexistenteException {
		Optional<Endereco> optional = enderecoRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new EnderecoInexistenteException();

		}
		return optional.get();
	}

	public void inserir(Endereco endereco) throws EnderecoExistenteException {
		enderecoRepositorio.save(endereco);			
	}

	public Endereco atualizar(Endereco endereco, Integer id) throws EnderecoInexistenteException, EnderecoExistenteException  {
        Optional<Endereco> optional = enderecoRepositorio.findById(id);
        if (optional.isEmpty()) {
            throw new EnderecoInexistenteException();
        }
        Endereco oldEndereco = optional.get();
        if (endereco.getCep() != null && !endereco.getCep().equals("")) {
            oldEndereco.setCep(endereco.getCep());
        }
        if (endereco.getRua() != null && !endereco.getRua().equals("")) {
            oldEndereco.setRua(endereco.getRua());
        }
        if (endereco.getBairro() != null && !endereco.getBairro().equals("")) {
            oldEndereco.setBairro(endereco.getBairro());
        }
        if (endereco.getCidade() != null && !endereco.getCidade().equals("")) {
            oldEndereco.setCidade(endereco.getCidade());
        }
        if (endereco.getEstado() != null && !endereco.getEstado().equals("")) {
            oldEndereco.setEstado(endereco.getEstado());
        }
        if (endereco.getNumero() != null && !endereco.getNumero().equals("")) {
            oldEndereco.setNumero(endereco.getNumero());
        }
        if (endereco.getComplemento() != null && !endereco.getComplemento().equals("")) {
            oldEndereco.setComplemento(endereco.getComplemento());
        }
        return enderecoRepositorio.save(oldEndereco);
	}

	public void deletar(Integer id) throws EnderecoInexistenteException  {
		Optional<Endereco> optional = enderecoRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new EnderecoInexistenteException();
        
		}
		enderecoRepositorio.deleteById(id);
	}
}
