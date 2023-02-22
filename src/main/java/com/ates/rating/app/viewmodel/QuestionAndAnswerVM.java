package com.ates.rating.app.viewmodel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionAndAnswerVM {

    private List<FeedbackQuestionVM> feedbackQuestionVM;
    private List<FeedbackOptionVM> feedbackOptionVM;
}
