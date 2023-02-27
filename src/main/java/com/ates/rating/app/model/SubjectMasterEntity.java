package com.ates.rating.app.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "SUBJECT_ENTITY")
@Where(clause = "status=true")
public class SubjectMasterEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String subjectCode;
    private String subject;
    private String specialization;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "semester_id")
    private SemesterEntity semesterEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;

    private String pattern;
    private Integer subjectMasterId;
    @Column(columnDefinition = "boolean default true")
    private boolean status = true;

}
