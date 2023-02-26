package com.ates.rating.app.service;

import com.ates.rating.app.security.security.services.UserDetailsImpl;
import com.ates.rating.app.viewmodel.*;
import org.springframework.stereotype.Component;

@Component
public interface QuestionAnswerService {

    Boolean saveAnswer(UserDetailsImpl userId, OptionVM optionVM);

    QuestionAndAnswerVM getAllQuestionAndAnswer(Long userId, String feedbackType);

    ChartDataVM getChartData(String chartType, Integer year);

    Boolean checkAlreadySubmit(UserDetailsImpl user, String feedbackType, Long year);

    SubjectWiseQuestionAnswerVM getQuestionsAndAnswerWithSemester(Long userId, String feedbackType, Long semesterId, Long year, Long classId);
}
