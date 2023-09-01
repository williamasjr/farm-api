package com.farm.api.ebytr.staff.controllers;

import com.farm.api.ebytr.staff.controllers.dto.CropDto;
import com.farm.api.ebytr.staff.controllers.dto.FarmDto;
import com.farm.api.ebytr.staff.models.entities.Crop;
import com.farm.api.ebytr.staff.models.entities.Farm;
import com.farm.api.ebytr.staff.service.CropService;
import com.farm.api.ebytr.staff.service.FarmService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * comment.
 */

@RestController
@RequestMapping(value = "/farms")
public class FarmController {

  private final FarmService farmService;

  private final CropService cropService;

  @Autowired
  public FarmController(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * comment.
   */

  @PostMapping
  public ResponseEntity<Farm> createFarm(@RequestBody FarmDto farmDto) {
    Farm newFarm = farmService.insertFarm(farmDto.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarm);
  }

  /**
   * Endpoint for create a new crop.
   */

  @PostMapping("/{farmId}/crops")
  public ResponseEntity<CropDto> createCrop(
      @PathVariable Long farmId,
      @RequestBody CropDto cropDto
  ) {

    Optional<Farm> farm = farmService.getFarmById(farmId);

    Crop crop = cropDto.toEntity();
    crop.setFarms(farm.get());
    crop.setPlantedDate(cropDto.toEntity().getPlantedDate());
    crop.setHarvestDate(cropDto.toEntity().getHarvestDate());

    Crop newCrop = cropService.insertCrop(crop);
    CropDto newCropDto = CropDto.fromEntity(newCrop);

    return ResponseEntity.status(HttpStatus.CREATED).body(newCropDto);
  }

  /**
   * comment.
   */

  @GetMapping()
  public List<FarmDto> getAllFarms() {
    List<Farm> getAll = farmService.getAllFarms();
    return getAll.stream()
        .map((farm -> new FarmDto(
            farm.getId(),
            farm.getName(),
            farm.getSize())))
        .collect(
            Collectors.toList());
  }

  /**
   * comment.
   */

  @GetMapping("/{farmId}/crops")
  public ResponseEntity<?> getAllCropsByFarmId(@PathVariable Long farmId) {
    Optional<Farm> farm = farmService.getFarmById(farmId);
    List<CropDto> crops = farm.get().getCrops().stream()
        .map(crop -> new CropDto(crop.getId(),
            crop.getName(), crop.getPlantedArea(),
            crop.getPlantedDate(), crop.getHarvestDate(), crop.getFarm().getId()))
        .collect(Collectors.toList());
    return ResponseEntity.ok(crops);
  }

  /**
   * comment.
   */

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Farm>> getFarmById(@PathVariable Long id) {
    Optional<Farm> optionalFarm = farmService.getFarmById(id);
    return ResponseEntity.status(HttpStatus.OK).body(optionalFarm);
  }

}
