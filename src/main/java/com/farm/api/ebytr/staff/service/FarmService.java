package com.farm.api.ebytr.staff.service;

import com.farm.api.ebytr.staff.exceptions.CustomException;
import com.farm.api.ebytr.staff.models.entities.Farm;
import com.farm.api.ebytr.staff.models.repositories.FarmRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * comment.
 */

@Service
public class FarmService {

  private final FarmRepository farmRepository;

  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  public Farm insertFarm(Farm farm) {
    return farmRepository.save(farm);
  }

  public List<Farm> getAllFarms() {
    return farmRepository.findAll();
  }

  /**
   * comment.
   */

  public Optional<Farm> getFarmById(Long farmId) throws CustomException {

    Optional<Farm> farm = farmRepository.findById(farmId);
    if (farm.isEmpty()) {
      throw new CustomException("Fazenda não encontrada!");
    }
    return farm;
  }
}