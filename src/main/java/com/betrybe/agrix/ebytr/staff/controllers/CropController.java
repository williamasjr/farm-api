package com.betrybe.agrix.ebytr.staff.controllers;

import com.betrybe.agrix.ebytr.staff.controllers.dto.CropDto;
import com.betrybe.agrix.ebytr.staff.models.entities.Crop;
import com.betrybe.agrix.ebytr.staff.service.CropService;
import com.betrybe.agrix.ebytr.staff.service.FarmService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * comment.
 */

@RestController
@RequestMapping("/crops")
public class CropController {

  CropService cropService;

  FarmService farmService;


  @Autowired
  public CropController(CropService cropService, FarmService farmService) {
    this.cropService = cropService;
    this.farmService = farmService;
  }

  /**
   * comment.
   */

  @GetMapping()
  public ResponseEntity<Stream<CropDto>> getAllCrops() {
    List<Crop> getAll = cropService.getAllCrops();
    Stream<CropDto> getAllCropsDto = getAll.stream()
        .map((crop -> new CropDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            crop.getFarm().getId())));

    return ResponseEntity.ok(getAllCropsDto);
  }

  /**
   * comment.
   */

  @GetMapping("/{id}")
  public ResponseEntity<Optional<CropDto>> getCropbyId(@PathVariable Long id) {
    Optional<CropDto> optionalCrop = cropService.getCropById(id);
    return ResponseEntity.status(HttpStatus.OK).body(optionalCrop);
  }

  /**
   * comment.
   */

  @GetMapping("/search")
  public ResponseEntity<List<CropDto>> search(@RequestParam LocalDate start,
      @RequestParam LocalDate end) {
    List<Crop> serachCrops = cropService.findByHarvestDate(start, end);
    List<CropDto> serachCropsDto = serachCrops.stream().map(CropDto::fromEntity)
        .collect(Collectors.toList());
    return ResponseEntity.status(HttpStatus.OK).body(serachCropsDto);
  }
}
