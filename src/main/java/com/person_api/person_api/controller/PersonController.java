package com.person_api.person_api.controller;

import com.person_api.person_api.entity.Person;
import com.person_api.person_api.entity.enums.GenderEnum;
import com.person_api.person_api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<Person>> findAll(){
        List<Person> list = personService.findAll();
        return ResponseEntity.ok().body(list);
    }

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

}
