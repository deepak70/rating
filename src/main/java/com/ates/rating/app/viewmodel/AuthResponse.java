package com.ates.rating.app.viewmodel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private Long userId;
    private String jwtToken;
    private String userName;
    private String name;
    private ClassList classList;
    private DepartmentListVM departmentListVM;
}
