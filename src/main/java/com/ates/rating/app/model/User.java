package com.ates.rating.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String password;
    private String emailId;
    private String username;
    private String mobileNumber;
    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;
    @ManyToOne
    @JoinColumn(name = "student_class_id")
    private ClassEntity studentClass;

    @Enumerated(EnumType.STRING)
    private Provider provider;
    private boolean isActive;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Role> roles;

    public User() {
    }

    public User(String username, String email, String password, String name) {
        this.username = username;
        this.emailId = email;
        this.password = password;
    }
}
