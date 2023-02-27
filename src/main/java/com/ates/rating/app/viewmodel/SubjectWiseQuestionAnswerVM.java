package com.ates.rating.app.viewmodel;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SubjectWiseQuestionAnswerVM {
    private Long totalNoOfQuestions;
    private List<SubjectQuestionAndAnswerVM> subjectQuestionAndAnswerVMS;
}
