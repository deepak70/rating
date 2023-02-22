package com.ates.rating.app.repository;

import com.ates.rating.app.model.FeedbackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FeedbackListRepository extends JpaRepository<FeedbackList, Long> {
    Optional<FeedbackList> findByFeedbackTypeContainingIgnoreCase(String feedbackType);

    Optional<FeedbackList> findByFeedbackType(String type);
}
