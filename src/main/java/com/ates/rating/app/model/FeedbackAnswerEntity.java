package com.ates.rating.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "FEEDBACK_ANSWER")
public class FeedbackAnswerEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String answer;
    private Integer weightage;
    @ManyToOne
    @JoinColumn(name = "feedback_list_id")
    private FeedbackList feedbackList;
}
