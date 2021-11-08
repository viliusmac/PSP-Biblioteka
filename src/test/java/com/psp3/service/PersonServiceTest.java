package com.psp3.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.psp3.model.Person;
import com.psp3.reposiotry.PersonRepository;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    @Test
    void testfindAll() {
        Person p = new Person(1, "Vardas", "Pavarde", "+37067777777", "email@mail.com", "Password123??");
        List<Person> people = new ArrayList<>();
        people.add(p);

        when(personRepository.findAll()).thenReturn(people);

        List<Person> found = personService.findAll();

        verify(personRepository).findAll();

        assertEquals(1, found.size());
    }

    @Test
    void testFindById() {
        Person p = new Person(1, "Vardas", "Pavarde", "+37067777777", "email@mail.com", "Password123??");
        when(personRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(p));

        Person found = personService.findById(1);
        verify(personRepository).findById(Mockito.anyLong());
        assertNotNull(found);
    }

    @Test
    void testAdd() {
        Person p = new Person(1, "Vardas", "Pavarde", "+37067777777", "email@mail.com", "Password123??");
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(p);

        Person added = personService.add(p);
        verify(personRepository).save(Mockito.any(Person.class));
        assertNotNull(added);
    }

    @Test
    void testDeleteById() {
        personService.deleteById(1);
        verify(personRepository).deleteById(Mockito.anyLong());
    }

    @Test
    void testUpdate() {
        Person p = new Person(1, "Vardas", "Pavarde", "+37067777777", "email@mail.com", "Password123??");
        when(personRepository.save(Mockito.any(Person.class))).thenReturn(p);

        Person updated = personService.update(p);
        verify(personRepository).save(Mockito.any(Person.class));
        assertNotNull(updated);
    }

}