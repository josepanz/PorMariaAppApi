package com.pormaria.api.crud.services;

import com.pormaria.api.crud.models.PersonModel;
import com.pormaria.api.crud.repositories.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    IPersonRepository iPersonRepository;

    public ArrayList<PersonModel> getAllPersons() {
        return (ArrayList<PersonModel>) iPersonRepository.findAll();
    }

    public PersonModel savePerson(PersonModel personModel) {
        return iPersonRepository.save(personModel);
    }

    public Optional<PersonModel> getPersonById(Long id) {
        return iPersonRepository.findById(id);
    }

    public Optional<PersonModel> updatePersonById(PersonModel request, Long id) {
        if (iPersonRepository.findById(id).isPresent()) {
            PersonModel person = iPersonRepository.findById(id).get();
            person.setBirthdate(request.getBirthdate());
            person.setCivilStatus(request.getCivilStatus());
            person.setNames(request.getNames());
            person.setLastNames(request.getLastNames());
            person.setPersonType(request.getPersonType());
            person.setActive(request.isActive());
            person.setDisabledDate(request.getDisabledDate());
            iPersonRepository.save(person);
            return Optional.of(person);
        }
        return Optional.empty();
    }

    public boolean deletePersonById(Long id) {
        try {
            iPersonRepository.deleteById(id);
            System.out.println("Person id deleted: " + id);
            return true;
        } catch (Exception e) {
            System.out.println("Person id cant deleted: " + id);
            return false;
        }
    }
}
