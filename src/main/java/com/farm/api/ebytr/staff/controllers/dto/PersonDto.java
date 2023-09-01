package com.farm.api.ebytr.staff.controllers.dto;

import com.farm.api.ebytr.staff.models.entities.Person;
import com.farm.api.ebytr.staff.security.Role;

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
