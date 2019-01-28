package com.dhaval.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dhaval.model.Person;
import com.dhaval.service.PersonService;

public class PersonController {
	@Autowired
	PersonService personService;

	@PostMapping(path = "/api/person")
	public ResponseEntity<Person> register(@RequestBody Person p) {
		return ResponseEntity.ok(personService.save(p));
	}

	@RequestMapping(path = "/api/hi")
	public String sayHi() {
		return "ABC";
	}

	@GetMapping(path = "/api/person")
	public ResponseEntity<List<Person>> getAllPersons() {
		return ResponseEntity.ok(personService.getAll());
	}

	@GetMapping(path = "/api/person/{person-id}")
	public ResponseEntity<Person> getPersonById(@PathVariable(name = "person-id", required = true) Long personId) {
		Person person = personService.findById(personId);
		if (person != null) {
			return ResponseEntity.ok(person);
		}
		return ResponseEntity.notFound().build();
	}

}
