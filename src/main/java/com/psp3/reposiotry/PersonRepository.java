package com.psp3.reposiotry;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.psp3.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
}
