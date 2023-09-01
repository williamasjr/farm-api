package com.farm.api.ebytr.staff.controllers.dto;

import com.farm.api.ebytr.staff.models.entities.Fertilizer;

/**
 * Comment.
 */

public record FertilizreDto(Long id, String name, String brand, String composition) {

  public Fertilizer toFertilizer() {
    return new Fertilizer(id, name, brand, composition);
  }
}
