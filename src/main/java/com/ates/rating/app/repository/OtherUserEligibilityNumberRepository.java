package com.ates.rating.app.repository;

import com.ates.rating.app.model.OtherUserEligibilityNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherUserEligibilityNumberRepository extends JpaRepository<OtherUserEligibilityNumber,Long> {
    Boolean existsByEligibilityNumber(String eligibilityNumber);
}
