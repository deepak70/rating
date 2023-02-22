package com.ates.rating.app.service.impl;

import com.ates.rating.app.exception.AppException;
import com.ates.rating.app.model.Provider;
import com.ates.rating.app.model.Role;
import com.ates.rating.app.model.RoleEnum;
import com.ates.rating.app.model.User;
import com.ates.rating.app.repository.ClassEntityRepository;
import com.ates.rating.app.repository.DepartmentRepository;
import com.ates.rating.app.repository.RoleRepository;
import com.ates.rating.app.repository.UserRepository;
import com.ates.rating.app.security.payload.request.SignupRequest;
import com.ates.rating.app.security.security.jwt.JwtUtils;
import com.ates.rating.app.service.CustomUserDetailsService;
import com.ates.rating.app.viewmodel.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    private final ClassEntityRepository classEntityRepository;
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder encoder;

    @Override
    public AuthResponse register(UserInfoVM userInfoVM) {
        return new AuthResponse();
    }

    @Override
    public AuthResponse registerUser(SignupRequest signUpRequest) {
        var alreadyRegisterUser = userRepository.findByUsername(signUpRequest.getEmail());
        if (alreadyRegisterUser.isPresent()) {
            return getTokenResponse(signUpRequest, alreadyRegisterUser.get());
        } else {
            User user = new User()
                    .setActive(true)
                    .setName(signUpRequest.getName())
                    .setRoles(roleSet())
                    .setPassword(encoder.encode(signUpRequest.getPassword()))
                    .setEmailId(signUpRequest.getEmail())
                    .setProvider(Provider.GOOGLE)
                    .setUsername(signUpRequest.getEmail());
            var departmentEntity = departmentRepository.findByDepartment("NA").orElseThrow(() -> new AppException("User not found "));
            user.setDepartment(departmentEntity);
            var classEntity = classEntityRepository.findByClassName("NA").orElseThrow(() -> new AppException("Class not found"));
            user.setStudentClass(classEntity);
            var newUser = userRepository.save(user);

            return getTokenResponse(signUpRequest, newUser);
        }
    }

    private AuthResponse getTokenResponse(SignupRequest signUpRequest, User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signUpRequest.getEmail(), signUpRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        var departmentList = new DepartmentListVM().setDepartmentVMS(Collections.singletonList(new DepartmentVM()
                .setDepartment(user.getDepartment().getDepartment())
                .setId(user.getDepartment().getId())
        ));
        var classList = new ClassList().setClassVM(Collections.singletonList(new ClassVM()
                .setClassName(user.getStudentClass().getClassName())
                .setId(user.getStudentClass().getId())
                .setDepartmentId(user.getStudentClass().getDepartmentEntity().getId())
        ));
        return new AuthResponse().setUserId(user.getId())
                .setJwtToken(jwt)
                .setDepartmentListVM(departmentList)
                .setClassList(classList)
                .setUserName(user.getUsername())
                .setName(user.getName());
    }

    private Set<Role> roleSet() {
        Set<Role> roles = new HashSet<>();
        var role = roleRepository.findByRoleName(RoleEnum.ROLE_USER)
                .orElseThrow(() -> new AppException("Role not found"));
        roles.add(role);
        return roles;

    }
}
