package com.ates.rating.app.controller;

import com.ates.rating.app.GenericResponse;
import com.ates.rating.app.service.CustomUserDetailsService;
import com.ates.rating.app.viewmodel.AuthResponse;
import com.ates.rating.app.viewmodel.UserInfoVM;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/public")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
public class PublicAPIController {

    private final CustomUserDetailsService userDetailsService;

    @PostMapping("/register")
    public GenericResponse<AuthResponse> singUp(@Valid  @RequestBody UserInfoVM userInfoVM) {
        var response = userDetailsService.register(userInfoVM);
        return GenericResponse.<AuthResponse>builder()
                .success(true)
                .data(response)
                .build();
    }
}
