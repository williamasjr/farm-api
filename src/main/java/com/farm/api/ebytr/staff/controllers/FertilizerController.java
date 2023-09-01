package com.farm.api.ebytr.staff.controllers;

import com.farm.api.ebytr.staff.controllers.dto.FertilizreDto;
import com.farm.api.ebytr.staff.models.entities.Fertilizer;
import com.farm.api.ebytr.staff.service.FertilizerService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
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
 * * Coment.
 */

@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {

  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  @PostMapping
  public ResponseEntity<Fertilizer> createFertilizer(@RequestBody FertilizreDto fertilizreDto) {
    Fertilizer saveFertilizer = fertilizerService.create(fertilizreDto.toFertilizer());
    return ResponseEntity.status(HttpStatus.CREATED).body(saveFertilizer);
  }

  /**
   * * Coment.
   */

  @GetMapping
  public ResponseEntity<Stream<FertilizreDto>> getAllFerlizers() {
    List<Fertilizer> allFertilizers = fertilizerService.findAll();
    Stream<FertilizreDto> allFertilizersDto = allFertilizers.stream()
        .map(fertilizer -> new FertilizreDto(
            fertilizer.getId(),
            fertilizer.getName(),
            fertilizer.getBrand(),
            fertilizer.getComposition()
        ));
    return ResponseEntity.ok(allFertilizersDto);
  }

  @GetMapping("/{fertilizerId}")
  public ResponseEntity<Optional<Fertilizer>> getFertilizerById(@PathVariable Long fertilizerId) {
    Optional<Fertilizer> optionalFertilizer = fertilizerService.getFertilizerById(fertilizerId);
    return ResponseEntity.status(HttpStatus.OK).body(optionalFertilizer);
  }
}
