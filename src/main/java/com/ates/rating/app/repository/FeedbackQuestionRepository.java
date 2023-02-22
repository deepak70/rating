package com.ates.rating.app.repository;

import com.ates.rating.app.model.FeedbackList;
import com.ates.rating.app.model.FeedbackQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackQuestionRepository extends JpaRepository<FeedbackQuestionEntity, Long> {
    List<FeedbackQuestionEntity> findAllByFeedbackList(FeedbackList feedbackList);
}
