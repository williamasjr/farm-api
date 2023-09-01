package com.farm.api.ebytr.staff.service;

import com.farm.api.ebytr.staff.controllers.dto.CropDto;
import com.farm.api.ebytr.staff.exceptions.CustomException;
import com.farm.api.ebytr.staff.models.entities.Crop;
import com.farm.api.ebytr.staff.models.repositories.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * comment.
 */

@Service
public class CropService {

  CropRepository cropRepository;

  @Autowired
  public CropService(CropRepository cropRepository) {
    this.cropRepository = cropRepository;
  }

  /**
   * comment.
   */

  public Crop insertCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  /**
   * comment.
   */

  public Optional<CropDto> getCropById(Long id) throws CustomException {
    return Optional.ofNullable(cropRepository
        .findById(id)
        .map(crop -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            crop.getFarm().getId()))
        .orElseThrow(() -> new CustomException("Plantação não encontrada!")));
  }

  public List<Crop> findByHarvestDate(LocalDate start, LocalDate end) {
    return cropRepository.findByHarvestDateBetween(start, end);
  }
}
