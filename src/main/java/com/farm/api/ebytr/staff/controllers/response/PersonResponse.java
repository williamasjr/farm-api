package com.farm.api.ebytr.staff.controllers.response;

import com.farm.api.ebytr.staff.models.entities.Person;
import com.farm.api.ebytr.staff.security.Role;

/**
 * Comment.
 */
public class PersonResponse {

  private Long id;

  private String username;

  private Role role;

  /**
   * Comment.
   */

  public PersonResponse(Person person) {
    this.id = person.getId();
    this.username = person.getUsername();
    this.role = person.getRole();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }
}