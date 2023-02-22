package com.ates.rating.app.repository;

import com.ates.rating.app.model.FeedbackAnswerEntity;
import com.ates.rating.app.model.FeedbackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackAnswerRepository extends JpaRepository<FeedbackAnswerEntity, Long> {
    List<FeedbackAnswerEntity> findAllByFeedbackList(FeedbackList feedbackList);
}
