package com.farm.api.ebytr.staff.controllers.dto;

import com.farm.api.ebytr.staff.models.entities.Farm;
import java.util.List;

/**
 * comment.
 */

public record FarmDto(Long id, String name, Double size) {

  public Farm toFarm() {
    return new Farm(id, name, size, List.of());
  }
}
