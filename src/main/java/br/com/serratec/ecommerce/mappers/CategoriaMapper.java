package br.com.serratec.ecommerce.mappers;

import org.springframework.stereotype.Component;

import br.com.serratec.ecommerce.dtos.CategoriaDTO;
import br.com.serratec.ecommerce.models.Categoria;

@Component
public class CategoriaMapper {
	
	public Categoria categoriaDtoToCategoria(CategoriaDTO categoriaDTO) {
		Categoria  categoria = new Categoria();
		categoria.setNome(categoriaDTO.getNome());
		categoria.setDescricao(categoriaDTO.getDescricao());
		
		return categoria;
	}
}
