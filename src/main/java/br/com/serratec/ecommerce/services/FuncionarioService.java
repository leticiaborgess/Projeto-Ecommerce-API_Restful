package br.com.serratec.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.exceptions.FuncionarioExistenteException;
import br.com.serratec.ecommerce.exceptions.FuncionarioInexistenteException;
import br.com.serratec.ecommerce.exceptions.UsuarioExistenteException;
import br.com.serratec.ecommerce.exceptions.UsuarioInexistenteException;
import br.com.serratec.ecommerce.models.Funcionario;
import br.com.serratec.ecommerce.repositories.FuncionarioRepositorio;

@Service
public class FuncionarioService {
	@Autowired
	FuncionarioRepositorio funcionarioRepositorio;
	
	@Autowired
	UsuarioService usuarioService;
	
	
	public void verificaExiste(String cpf) throws FuncionarioExistenteException {
		Optional<Funcionario> optional = funcionarioRepositorio.findByCpf(cpf);
		if (optional.isPresent()) {
			throw new FuncionarioExistenteException();
		}
	}

	public List<Funcionario> listarTudo() {
		return funcionarioRepositorio.findAll();
	}

	public Funcionario listar(Integer id) throws FuncionarioInexistenteException{
		Optional<Funcionario> optional = funcionarioRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new FuncionarioInexistenteException();

		}
		return optional.get();
	}
	
	public void inserir(Funcionario funcionario) throws FuncionarioExistenteException, UsuarioExistenteException{
		verificaExiste(funcionario.getCpf());
		usuarioService.inserir(funcionario.getUsuario());
		funcionarioRepositorio.save(funcionario);
	}

	public Funcionario atualizar(Funcionario funcionario, Integer id) throws FuncionarioInexistenteException, FuncionarioExistenteException, UsuarioInexistenteException, UsuarioExistenteException {
        Optional<Funcionario> optional = funcionarioRepositorio.findById(id);
        if (optional.isEmpty()) {
            throw new FuncionarioInexistenteException();
        }
        Funcionario oldFuncionario = optional.get();
        if (funcionario.getNome() != null && !funcionario.getNome().equals("")) {
            oldFuncionario.setNome(funcionario.getNome());
        }
        if (funcionario.getTelefone() != null && !funcionario.getTelefone().equals("")) {
            oldFuncionario.setTelefone(funcionario.getTelefone());
        }
        if (funcionario.getDataNascimento() != null) {
            oldFuncionario.setDataNascimento(funcionario.getDataNascimento());
        }
        
        usuarioService.atualizar(funcionario.getUsuario(), id);
        return funcionarioRepositorio.save(oldFuncionario);
	}

	public void deletar(Integer id) throws FuncionarioInexistenteException {
		Optional<Funcionario> optional = funcionarioRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new FuncionarioInexistenteException();
        
		}
		funcionarioRepositorio.deleteById(id);
	}
}
