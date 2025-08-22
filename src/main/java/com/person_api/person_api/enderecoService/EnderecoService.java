package com.person_api.person_api.enderecoService;

import com.person_api.person_api.client.ViaCepFeign;
import com.person_api.person_api.client.dto.endereco.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    @Autowired
    private ViaCepFeign viaCepFeign;

    public Endereco buscarEndereco(String cep){
        return viaCepFeign.buscarEnderecoPorCep(cep);
    }
}
