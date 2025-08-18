package com.person_api.person_api.controller;

import com.person_api.person_api.entity.Person;
import com.person_api.person_api.entity.enums.GenderEnum;
import com.person_api.person_api.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @Operation(description = "Retorna todas as pessoas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tras todas as pessoas cadastradas"),
            @ApiResponse(responseCode = "400", description = "Not found caso seja passada URL incorreta")
    })
    @GetMapping
    public ResponseEntity<List<Person>> findAll(){
        List<Person> list = personService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @Operation(description = "Retorna a pessoa pelo Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna pessoa pelo Id digitado"),
            @ApiResponse(responseCode = "500", description = "Id não existe")
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id){
        Person person = personService.findById(id);
        return ResponseEntity.ok().body(person);
    }

    @PostMapping
    public ResponseEntity<Person> insert(@RequestBody Person person){
        person = personService.insert(person);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Person> update(@PathVariable Long id, @RequestBody Person person){
        person = personService.update(id, person);
        return ResponseEntity.ok().body(person);
    }

}
