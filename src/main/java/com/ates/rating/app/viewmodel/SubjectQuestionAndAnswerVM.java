package com.ates.rating.app.viewmodel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubjectQuestionAndAnswerVM {
    private Long subjectId;
    private String subject;
    private List<FeedbackQuestionVM> feedbackQuestionVM;
    private List<FeedbackOptionVM> feedbackOptionVMS;
}
