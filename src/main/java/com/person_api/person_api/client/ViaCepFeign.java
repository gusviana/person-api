package com.person_api.person_api.client;

import com.person_api.person_api.client.dto.endereco.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepFeign {

    @GetMapping("/{cep}/json")
    Endereco buscarEnderecoPorCep(@PathVariable("cep") String cep);
}
