package br.com.serratec.ecommerce.restClient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.serratec.ecommerce.dtos.ViaCepDTO;

@Component
public class RestViaCep {
	
	public ViaCepDTO getViaCep(String cep) {
		String url =  "https://viacep.com.br/ws/" + cep + "/json";
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(url, ViaCepDTO.class);
	}
}
