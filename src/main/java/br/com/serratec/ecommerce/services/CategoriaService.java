package br.com.serratec.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.exceptions.CategoriaExistenteException;
import br.com.serratec.ecommerce.exceptions.CategoriaInexistenteException;
import br.com.serratec.ecommerce.exceptions.IdNotFoundException;
import br.com.serratec.ecommerce.models.Categoria;
import br.com.serratec.ecommerce.repositories.CategoriaRepositorio;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepositorio categoriaRepositorio;

	public List<Categoria> listarCategoria() {
		return categoriaRepositorio.findAll();
	}

	public Categoria listarId(Integer id) throws CategoriaInexistenteException {
		Optional<Categoria> optional = categoriaRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new CategoriaInexistenteException(); // TODO tratar

		}
		return optional.get();
	}

	public void verificarCategoriaExiste(String nome) throws CategoriaExistenteException {
		Optional<Categoria> optional = categoriaRepositorio.findByNome(nome);
		if (optional.isPresent()) {
			throw new CategoriaExistenteException();
		}
	}

	public void inserir(Categoria categoria) throws CategoriaExistenteException {
		verificarCategoriaExiste(categoria.getNome());
		categoriaRepositorio.save(categoria);
	}

	public Categoria atualizar(Categoria categoria, String nome) throws CategoriaInexistenteException, CategoriaExistenteException  {
        Optional<Categoria> optional = categoriaRepositorio.findByNome(nome);
        if (optional.isEmpty()) {
            throw new CategoriaInexistenteException();
        }
        Categoria oldCategoria = optional.get();
        if (!categoria.getNome().equals("") && categoria.getNome() != null) {
            verificarCategoriaExiste(categoria.getNome());
            oldCategoria.setNome(categoria.getNome());
        }
        if (!categoria.getDescricao().equals("") && categoria.getDescricao() != null) {
            oldCategoria.setDescricao(categoria.getDescricao());
        }
        return categoriaRepositorio.save(categoria);
	}

	public void deletar(Integer id) throws CategoriaInexistenteException  {
		Optional<Categoria> optional = categoriaRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new CategoriaInexistenteException();
        
		}
		categoriaRepositorio.deleteById(id);
	}
}
