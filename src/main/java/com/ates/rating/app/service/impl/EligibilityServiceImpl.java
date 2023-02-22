package com.ates.rating.app.service.impl;

import com.ates.rating.app.repository.EligibilityRepository;
import com.ates.rating.app.service.EligibilityService;
import com.ates.rating.app.service.FeedbackListService;
import com.ates.rating.app.service.OtherUserEligibilityNumberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EligibilityServiceImpl implements EligibilityService {

    private final EligibilityRepository eligibilityRepository;
    private final FeedbackListService feedbackListService;
    private final OtherUserEligibilityNumberService otherUserEligibilityNumberService;

    @Override
    public boolean isValidEligibility(String number, String userType) {
        if (feedbackListService.checkIsStudentEligibility(userType)) {
            return eligibilityRepository.existsByEligibilityNo(number);
        } else {
            return otherUserEligibilityNumberService.isEligibilityPass(number);
        }
    }
}
