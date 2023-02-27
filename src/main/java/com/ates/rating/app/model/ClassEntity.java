package com.ates.rating.app.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "CLASS_ENTITY")
public class ClassEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String className;
    private Boolean active;
    private Integer classId;

    @ManyToOne
    @JoinColumn(name = "department_entity_id", nullable = false)
    private DepartmentEntity departmentEntity;


}
