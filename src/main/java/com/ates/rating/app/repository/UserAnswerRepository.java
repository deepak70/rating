package com.ates.rating.app.repository;

import com.ates.rating.app.model.FeedbackList;
import com.ates.rating.app.model.User;
import com.ates.rating.app.model.UserOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswerRepository extends JpaRepository<UserOption, Long> {

    List<UserOption> findAllByUserAndYear(User currentUser, int currentYear);

    List<UserOption> findAllByYearAndFeedback(Integer year, FeedbackList feedbackList);
}
