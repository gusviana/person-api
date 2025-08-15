package com.person_api.person_api.controller;

import com.person_api.person_api.entity.Person;
import com.person_api.person_api.entity.enums.GenderEnum;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    @GetMapping
    public ResponseEntity<Person> findAll(){
        Person p = new Person(null, GenderEnum.MALE, "350.522.908-37", LocalDate.of(1995, 2, 1), "Gustavo Viana");
        return ResponseEntity.ok().body(p);
    }

}
