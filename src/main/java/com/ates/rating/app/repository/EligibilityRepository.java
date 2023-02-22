package com.ates.rating.app.repository;

import com.ates.rating.app.model.EligibilityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EligibilityRepository extends JpaRepository<EligibilityEntity, Long> {
    Boolean existsByEligibilityNo(String number);
}
