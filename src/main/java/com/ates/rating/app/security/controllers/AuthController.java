package com.ates.rating.app.security.controllers;

import com.ates.rating.app.GenericResponse;
import com.ates.rating.app.repository.RoleRepository;
import com.ates.rating.app.repository.UserRepository;
import com.ates.rating.app.security.payload.request.SignupRequest;
import com.ates.rating.app.security.security.jwt.JwtUtils;
import com.ates.rating.app.service.CustomUserDetailsService;
import com.ates.rating.app.viewmodel.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/signup")
    public GenericResponse<AuthResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        var data = userDetailsService.registerUser(signUpRequest);
        return GenericResponse.<AuthResponse>builder()
                .data(data)
                .build();
    }
}
