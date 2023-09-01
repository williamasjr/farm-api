package com.farm.api.ebytr.staff.models.repositories;

import com.farm.api.ebytr.staff.models.entities.Farm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * comment.
 */

@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

}
