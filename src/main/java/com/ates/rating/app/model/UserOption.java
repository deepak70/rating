package com.ates.rating.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "USER_OPTION")
public class UserOption extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "feedback_answer_id")
    private FeedbackAnswerEntity feedbackAnswer;

    @ManyToOne
    @JoinColumn(name = "feedback_question_entity_id")
    private FeedbackQuestionEntity feedbackQuestionEntity;

    @ManyToOne
    @JoinColumn(name = "feedback_type_id")
    private FeedbackList feedback;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    private Integer year;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "semester_entity_id")
    private SemesterEntity semesterEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_master_entity_id")
    private SubjectMasterEntity subjectMasterEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_entity_id")
    private ClassEntity classEntity;
}
