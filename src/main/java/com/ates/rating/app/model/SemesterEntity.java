package com.ates.rating.app.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "semester_entity")
@Where(clause = "active=true")
public class SemesterEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String semester;
    private Integer semesterId;
    @Column(columnDefinition = "boolean default true")
    private boolean active=true;
}
