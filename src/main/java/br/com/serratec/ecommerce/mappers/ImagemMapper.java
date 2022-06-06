package br.com.serratec.ecommerce.mappers;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.com.serratec.ecommerce.models.Imagem;

@Component
public class ImagemMapper {
	
	public Imagem multipartFileToImagem(MultipartFile file) throws IOException {
		Imagem imagem = new Imagem();
		imagem.setNome(file.getName());
		imagem.setMimeType(file.getContentType());
		imagem.setData(file.getBytes());
		
		return imagem;
	}
}
