package com.ates.rating.app.viewmodel;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AuthResponse {
    private Long userId;
    private String jwtToken;
    private String userName;
    private String name;
    private Set<String> role;
    private ClassList classList;
    private DepartmentListVM departmentListVM;
}
