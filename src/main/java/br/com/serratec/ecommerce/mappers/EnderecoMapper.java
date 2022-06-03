package br.com.serratec.ecommerce.mappers;

import org.springframework.stereotype.Component;

import br.com.serratec.ecommerce.dtos.ViaCepDTO;
import br.com.serratec.ecommerce.models.Endereco;

@Component
public class EnderecoMapper {
	
	public Endereco ViaCepDtoToEndereco(ViaCepDTO viaCepDTO) {
		Endereco endereco = new Endereco();
		
		endereco.setCep(viaCepDTO.getCep());
		endereco.setRua(viaCepDTO.getLogradouro());
		endereco.setBairro(viaCepDTO.getBairro());
		endereco.setCidade(viaCepDTO.getLocalidade());
		endereco.setEstado(viaCepDTO.getUf());
		
		return endereco;
	}
}
