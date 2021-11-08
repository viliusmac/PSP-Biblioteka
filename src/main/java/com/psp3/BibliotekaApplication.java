package com.psp3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.psp3.model.Person;
import com.psp3.reposiotry.PersonRepository;

@SpringBootApplication
public class BibliotekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotekaApplication.class, args);
	}

	@Profile("!test")
	@Bean
	CommandLineRunner runner(PersonRepository repository) {
		return args -> {
			repository.save(new Person(1, "Vardas", "Pavarde", "+37067777777", "email@mail.com", "Password123??"));
			repository.save(new Person(2, "Vardas2", "Pavarde2", "+37061111111", "name@yahoo.lt", "Word555@a"));
		};
	}

}
