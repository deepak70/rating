package com.ates.rating.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "OTHER_USER_ELIGIBILITY_NUMBER")
@Entity
public class OtherUserEligibilityNumber extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String eligibilityNumber;
    private Boolean active;
}
