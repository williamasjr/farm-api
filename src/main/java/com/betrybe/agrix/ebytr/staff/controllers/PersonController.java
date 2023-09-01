package com.betrybe.agrix.ebytr.staff.controllers;

import com.betrybe.agrix.ebytr.staff.controllers.dto.PersonDto;
import com.betrybe.agrix.ebytr.staff.controllers.response.PersonResponse;
import com.betrybe.agrix.ebytr.staff.models.entities.Person;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Comment.
 */

@RestController
@RequestMapping("/persons")
public class PersonController {

  PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  /**
   * Comment.
   */

  @PostMapping
  public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonDto person) {

    Person createdPerson = personService.create(person.toEntity());
    PersonResponse personResponse = new PersonResponse(createdPerson);
    return ResponseEntity.status(HttpStatus.CREATED).body(personResponse);
  }
}
