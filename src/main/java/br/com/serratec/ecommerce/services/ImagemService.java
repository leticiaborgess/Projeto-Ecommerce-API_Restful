package br.com.serratec.ecommerce.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.serratec.ecommerce.exceptions.ImagemExistenteException;
import br.com.serratec.ecommerce.exceptions.ImagemInexistenteException;
import br.com.serratec.ecommerce.models.Imagem;
import br.com.serratec.ecommerce.repositories.ImagemRepositorio;

@Service
public class ImagemService {
	
	@Autowired
	ImagemRepositorio imagemRepositorio;

	public void verificaExiste(String nome) throws ImagemExistenteException {
		Optional<Imagem> optional = imagemRepositorio.findByNome(nome);
		if (optional.isPresent()) {
			throw new ImagemExistenteException();
		}
	}

	public List<Imagem> listarTudo() {
		return imagemRepositorio.findAll();
	}

	public Imagem listar(Integer id) throws ImagemInexistenteException {
		Optional<Imagem> optional = imagemRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new ImagemInexistenteException();

		}
		return optional.get();
	}

	public void inserir(Imagem imagem) throws ImagemExistenteException {
		verificaExiste(imagem.getNome());
		imagemRepositorio.save(imagem);
	}

	public Imagem atualizar(Imagem imagem, Integer id)
			throws ImagemInexistenteException, ImagemExistenteException {
		Optional<Imagem> optional = imagemRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new ImagemInexistenteException();
		}
		Imagem oldImagem = optional.get();
		if (imagem.getNome() != null && !imagem.getNome().equals("")) {
			verificaExiste(imagem.getNome());
			oldImagem.setNome(imagem.getNome());
		}
		
		return imagemRepositorio.save(oldImagem);
	}

	public void deletar(Integer id) throws ImagemInexistenteException {
		Optional<Imagem> optional = imagemRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new ImagemInexistenteException();

		}
		imagemRepositorio.deleteById(id);
	}
	
	public String geraUrl(Integer id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produto/{id}/image").buildAndExpand(id).toUri();
		return uri.toString();
	}
}
