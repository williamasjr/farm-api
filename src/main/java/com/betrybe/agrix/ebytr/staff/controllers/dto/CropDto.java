package com.betrybe.agrix.ebytr.staff.controllers.dto;

import com.betrybe.agrix.ebytr.staff.models.entities.Crop;
import java.time.LocalDate;

/**
 * comment.
 */

public record CropDto(
    Long id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate,
    Long farmId) {

  /**
   * comment.
   */

  public static CropDto fromEntity(Crop crop) {
    return new CropDto(crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId());
  }

  /**
   * comment.
   */

  public Crop toEntity() {
    Crop crop = new Crop();
    crop.setId(id);
    crop.setName(name);
    crop.setPlantedArea(plantedArea);
    crop.setPlantedDate(plantedDate);
    crop.setHarvestDate(harvestDate);
    return crop;
  }
}
