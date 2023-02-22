package com.ates.rating.app.service.impl;

import com.ates.rating.app.exception.AppException;
import com.ates.rating.app.repository.FeedbackListRepository;
import com.ates.rating.app.service.FeedbackListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class FeedbackListServiceImpl implements FeedbackListService {

    private final FeedbackListRepository feedbackListRepository;

    @Override
    public Boolean checkIsStudentEligibility(String feedbackType) {
        var feedbackList = feedbackListRepository.findByFeedbackTypeContainingIgnoreCase(feedbackType.toLowerCase());
        System.out.println("feedbackList = " + feedbackList);
        if (feedbackList.isPresent())
            return feedbackList.get().getIsStudentEligibility();
        else
            throw new AppException("Invalid Feedback Type " + feedbackType);
    }
}
