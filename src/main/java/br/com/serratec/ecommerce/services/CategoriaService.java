package br.com.serratec.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.exceptions.CategoriaExistenteException;
import br.com.serratec.ecommerce.exceptions.CategoriaInexistenteException;
import br.com.serratec.ecommerce.models.Categoria;
import br.com.serratec.ecommerce.repositories.CategoriaRepositorio;

@Service
public class CategoriaService {
	@Autowired
	CategoriaRepositorio categoriaRepositorio;

	public void verificaExiste(String nome) throws CategoriaExistenteException {
		Optional<Categoria> optional = categoriaRepositorio.findByNome(nome);
		if (optional.isPresent()) {
			throw new CategoriaExistenteException();
		}
	}

	public List<Categoria> listarTudo() {
		return categoriaRepositorio.findAll();
	}

	public Categoria listar(Integer id) throws CategoriaInexistenteException {
		Optional<Categoria> optional = categoriaRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new CategoriaInexistenteException(); // TODO tratar

		}
		return optional.get();
	}

	public void inserir(Categoria categoria) throws CategoriaExistenteException {
		verificaExiste(categoria.getNome());
		categoriaRepositorio.save(categoria);
	}

	public Categoria atualizar(Categoria categoria, Integer id)
			throws CategoriaInexistenteException, CategoriaExistenteException {
		Optional<Categoria> optional = categoriaRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new CategoriaInexistenteException();
		}
		Categoria oldCategoria = optional.get();
		if (categoria.getNome() != null && !categoria.getNome().equals("")) {
			verificaExiste(categoria.getNome());
			oldCategoria.setNome(categoria.getNome());
		}
		if (categoria.getDescricao() != null && !categoria.getDescricao().equals("")) {
			oldCategoria.setDescricao(categoria.getDescricao());
		}
		return categoriaRepositorio.save(oldCategoria);
	}

	public void deletar(Integer id) throws CategoriaInexistenteException {
		Optional<Categoria> optional = categoriaRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new CategoriaInexistenteException();

		}
		categoriaRepositorio.deleteById(id);
	}
}
