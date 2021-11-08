package com.psp3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.psp3.model.Person;
import com.psp3.reposiotry.PersonRepository;
import com.psp3.util.uzduotis.main.Validator;

@Service
public class PersonService {

    PersonRepository personRepository;
    Validator validator = new Validator();

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return (List<Person>) personRepository.findAll();
    }

    public Person findById(long id) {
        return personRepository.findById(id).get();
    }

    public Person add(Person person) {
        if (validator.emailChecker(person.getEmailAddress()) && validator.passwordChecker(person.getPassword()) && validator.phoneNumberChecker(person.getPhoneNumber())) {
            return personRepository.save(person);
        }
        return null;
    }

    public void deleteById(long id) {
        personRepository.deleteById(id);
    }

    public Person update(Person person) {
        if (validator.emailChecker(person.getEmailAddress()) && validator.passwordChecker(person.getPassword()) && validator.phoneNumberChecker(person.getPhoneNumber())) {
            return personRepository.save(person);
        }
        return null;
    }

}
