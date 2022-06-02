package br.com.serratec.ecommerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.exceptions.ProdutoExistenteException;
import br.com.serratec.ecommerce.exceptions.ProdutoInexistenteException;
import br.com.serratec.ecommerce.models.Produto;
import br.com.serratec.ecommerce.repositories.ProdutoRepositorio;

@Service
public class ProdutoService {
	@Autowired
	ProdutoRepositorio produtoRepositorio;

	public void verificaExiste(String nome) throws ProdutoExistenteException {
		Optional<Produto> optional = produtoRepositorio.findByNome(nome);
		if (optional.isPresent()) {
			throw new ProdutoExistenteException();
		}
	}

	public List<Produto> listarTudo() {
		return produtoRepositorio.findAll();
	}

	public Produto listar(Integer id) throws ProdutoInexistenteException {
		Optional<Produto> optional = produtoRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new ProdutoInexistenteException(); // TODO tratar

		}
		return optional.get();
	}

	public void inserir(Produto produto) throws ProdutoExistenteException {
		verificaExiste(produto.getNome());
		produtoRepositorio.save(produto);
		// TODO com imagem
	}

	public Produto atualizar(Produto produto, Integer id)
			throws ProdutoInexistenteException, ProdutoExistenteException {
		Optional<Produto> optional = produtoRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new ProdutoInexistenteException();
		}
		Produto oldProduto = optional.get();
		if (produto.getNome() != null && !produto.getNome().equals("")) {
			verificaExiste(produto.getNome());
			oldProduto.setNome(produto.getNome());
		}
		if (produto.getDescricao() != null && !produto.getDescricao().equals("")) {
			oldProduto.setDescricao(produto.getDescricao());
		}
		
		if (produto.getPreco() != null && (produto.getPreco() > 0)) {
			oldProduto.setPreco(produto.getPreco());
		}
		if (produto.getQntEstoque() != null && !(produto.getQntEstoque() < 0)){
			oldProduto.setQntEstoque(produto.getQntEstoque());
		}
		if (produto.getDataCadastro() != null) {
			oldProduto.setDataCadastro(produto.getDataCadastro());
		}
		if (produto.getFuncionario() != null) {
			oldProduto.setFuncionario(produto.getFuncionario());
		}
		if (produto.getCategoria() != null) {
			oldProduto.setCategoria(produto.getCategoria());
		}
		return produtoRepositorio.save(oldProduto);
	}

	public void deletar(Integer id) throws ProdutoInexistenteException {
		Optional<Produto> optional = produtoRepositorio.findById(id);
		if (optional.isEmpty()) {
			throw new ProdutoInexistenteException();

		}
		produtoRepositorio.deleteById(id);
	}
}
