package com.person_api.person_api.enderecoController;

import com.person_api.person_api.client.dto.endereco.Endereco;
import com.person_api.person_api.enderecoService.EnderecoService;
import com.person_api.person_api.enderecoService.EnderecoServiceRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EnderecoServiceRestTemplate enderecoServiceRestTemplate;

    @GetMapping("/resttemplate/{cep}")
    public Endereco buscarComRestTemplate(@PathVariable String cep) {
        return enderecoServiceRestTemplate.buscarEnderecoPorCep(cep);
    }

    @GetMapping("/feign/{cep}")
    public Endereco buscarComFeign(@PathVariable String cep) {
        return enderecoService.buscarEndereco(cep);
    }
}
