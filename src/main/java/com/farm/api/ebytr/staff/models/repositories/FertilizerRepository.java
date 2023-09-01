package com.farm.api.ebytr.staff.models.repositories;

import com.farm.api.ebytr.staff.models.entities.Fertilizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * comment.
 */

@Repository
public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {

}
