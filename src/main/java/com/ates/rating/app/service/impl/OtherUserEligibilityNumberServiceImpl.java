package com.ates.rating.app.service.impl;

import com.ates.rating.app.repository.OtherUserEligibilityNumberRepository;
import com.ates.rating.app.service.OtherUserEligibilityNumberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OtherUserEligibilityNumberServiceImpl implements OtherUserEligibilityNumberService {
private final OtherUserEligibilityNumberRepository otherUserEligibilityNumberRepository;
    @Override
    public Boolean isEligibilityPass(String eligibilityNumber) {
        return otherUserEligibilityNumberRepository.existsByEligibilityNumber(eligibilityNumber);
    }
}
