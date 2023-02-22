package com.ates.rating.app.service;

import com.ates.rating.app.security.security.services.UserDetailsImpl;
import com.ates.rating.app.viewmodel.ChartDataVM;
import com.ates.rating.app.viewmodel.OptionVM;
import com.ates.rating.app.viewmodel.QuestionAndAnswerVM;
import org.springframework.stereotype.Component;

@Component
public interface QuestionAnswerService {

    Boolean saveAnswer(UserDetailsImpl userId, OptionVM optionVM);

    QuestionAndAnswerVM getAllQuestionAndAnswer(Long userId, String feedbackType);

    ChartDataVM getChartData(String chartType, Integer year);

    Boolean checkAlreadySubmit(UserDetailsImpl user, String feedbackType,Long year);
}
