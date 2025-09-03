package com.person_api.person_api.controller;

import com.person_api.person_api.dto.PersonDto;
import com.person_api.person_api.entity.Person;
import com.person_api.person_api.entity.PersonCar;
import com.person_api.person_api.entity.enums.GenderEnum;
import com.person_api.person_api.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name = "Person", description = "Endpoints para gerenciar pessoas")
@RequestMapping(value = "/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @Operation(summary = "Retorna todas as pessoas cadastradas", description = "Retorna todas as pessoas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tras todas as pessoas cadastradas"),
            @ApiResponse(responseCode = "400", description = "Not found caso seja passada URL incorreta")
    })
    @GetMapping
    public ResponseEntity<List<PersonDto>> findAll(){
        List<PersonDto> list = personService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(summary = "Retorna a pessoa pelo Id",description = "Retorna a pessoa pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna pessoa pelo Id digitado"),
            @ApiResponse(responseCode = "404", description = "Id não existe")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable Long id){
        PersonDto person = personService.findById(id);
        return ResponseEntity.ok().body(person);
    }

    @Operation(summary = "retorna pessoa por cpf",description = "Busca uma pessoa pelo CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada"),
            @ApiResponse(responseCode = "400", description = "CPF inválido")
    })
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<PersonDto> findByCpf(@PathVariable String cpf) {
        PersonDto person = personService.findByCpf(cpf);
        return ResponseEntity.ok(person);
    }

    @Operation(summary = "retorna pessoa com as 3 letras iniciais",description = "retorna pessoa com as 3 letras iniciais")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
            @ApiResponse(responseCode = "404", description = "Não há pessoa com este prefixo"),
            @ApiResponse(responseCode = "400", description = "prefixo inválido")
    })
    @GetMapping("/prefix/{prefix}")
    public ResponseEntity <List<PersonDto>> findByPrefix(@RequestParam String prefix){
        List<PersonDto> list = personService.findByPrefix(prefix);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Insere uma nova pessoa", description = "Insere uma nova pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "pessoa criada com sucesso"),
            @ApiResponse(responseCode = "500", description = "dados passados incorretamente"),
            @ApiResponse(responseCode = "409", description = "CPF já cadastrado")
    })
    @PostMapping
    public ResponseEntity<PersonDto> insert(@RequestBody PersonDto person){
        person = personService.insert(person);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(person);
    }

    @Operation(summary = "Deleta uma pessoa por Id",description = "Deleta uma pessoa por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Id não existe")
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza uma pessoa por Id",description = "Atualiza uma pessoa por Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa Atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "409", description = "CPF já cadastrado")
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonDto> update(@PathVariable Long id, @RequestBody PersonDto dto){
        dto = personService.update(id, dto);
        return ResponseEntity.ok().body(dto);
    }


    @PostMapping("/{cpf}/cars/{placa}")
    public ResponseEntity<PersonCar> linkCarToPerson(@PathVariable String cpf, @PathVariable String placa){
        PersonCar personCar = personService.linkPersonToCar(cpf, placa);
        return ResponseEntity.ok(personCar);
    }

    @GetMapping("/{cpf}/cars")
    public ResponseEntity<List<PersonCar>>getCarByPerson(@PathVariable String cpf){
        List<PersonCar> car = personService.getCarByPerson()
        return ResponseEntity.ok(car);
    }

    //vc VAI ENVIAR NO BODY O CPF E PLACA
    // SE A PESSOA EXISTE, E SE O PLACA EXISTE
    // SE SALVAR NA ENTIDADE CPF PLACA

    //CRIAR GET NESSA TABELA, PASSANDO O CPF

    //VAI RETORNAR A PESSOA + CARRO

}
