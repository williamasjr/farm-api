package com.betrybe.agrix.ebytr.staff.controllers.dto;

import com.betrybe.agrix.ebytr.staff.models.entities.Person;
import com.betrybe.agrix.ebytr.staff.security.Role;

/**
 * Comment.
 */

public record PersonDto(Long id, String username, String password, Role role) {

  /**
   * Comment.
   */

  public Person toEntity() {
    return new Person(id(), username(), password(), role());
  }
}
