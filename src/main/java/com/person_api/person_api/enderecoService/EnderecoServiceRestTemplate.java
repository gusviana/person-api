package com.person_api.person_api.enderecoService;

import com.person_api.person_api.client.dto.endereco.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EnderecoServiceRestTemplate {

    @Autowired
    private RestTemplate restTemplate;

    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/{cep}/json";

    public Endereco buscarEnderecoPorCep(String cep) {
        return restTemplate.getForObject(VIA_CEP_URL, Endereco.class, cep);
    }
}
