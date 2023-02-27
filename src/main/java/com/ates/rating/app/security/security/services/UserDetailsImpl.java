package com.ates.rating.app.security.security.services;

import com.ates.rating.app.model.User;
import com.ates.rating.app.viewmodel.ClassList;
import com.ates.rating.app.viewmodel.ClassVM;
import com.ates.rating.app.viewmodel.DepartmentListVM;
import com.ates.rating.app.viewmodel.DepartmentVM;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final Long id;

    private final String username;

    private final String email;
    private final ClassList classList;
    private final DepartmentListVM departmentListVM;
    @JsonIgnore
    private final String password;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities, ClassList classList, DepartmentListVM departmentListVM) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.departmentListVM = departmentListVM;
        this.classList = classList;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
                .collect(Collectors.toList());
        var classList = new ClassList().setClassVM(Collections
                .singletonList(new ClassVM().setClassName(user.getStudentClass().getClassName())
                        .setId(user.getStudentClass().getId())
                        .setDepartmentId(user.getStudentClass().getDepartmentEntity().getId())));
        var departmentList = new DepartmentListVM().setDepartmentVMS(Collections
                .singletonList(new DepartmentVM()
                        .setDepartment(user.getDepartment().getDepartment())
                        .setId(user.getDepartment().getId())
                ));
        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getEmailId(),
                user.getPassword(),
                authorities,
                classList,
                departmentList
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public ClassList getClassList() {
        return classList;
    }

    public DepartmentListVM getDepartmentListVM() {
        return departmentListVM;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
