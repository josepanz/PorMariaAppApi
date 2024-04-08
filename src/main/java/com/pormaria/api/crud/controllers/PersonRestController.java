package com.pormaria.api.crud.controllers;

import com.pormaria.api.crud.models.PersonModel;
import com.pormaria.api.crud.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonRestController {


    @Autowired
    private PersonService personService;

    @GetMapping(path = "/getAllPersons")
    public ArrayList<PersonModel> getAllPersons() {
        return this.personService.getAllPersons();
    }

    @PostMapping(path = "/postPerson")
    public PersonModel insertPerson(@RequestBody PersonModel person) {
        return this.personService.savePerson(person);
    }

    @GetMapping(path = "/getPerson/{id}")
    public Optional<PersonModel> getPersonById(@PathVariable("id") Long id) {
        return this.personService.getPersonById(id);
    }

    @PutMapping(path = "/update/{id}")
    public Optional<PersonModel> updatePersonById(@RequestBody PersonModel request, @PathVariable("id") Long id) {
        return this.personService.updatePersonById(request, id);
    }

    @DeleteMapping(path = "/delete/{id}")
    public boolean deletePersonById(@PathVariable("id") Long id) {
        return this.personService.deletePersonById(id);
    }

}
