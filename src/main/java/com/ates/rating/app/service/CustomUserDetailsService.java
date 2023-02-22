package com.ates.rating.app.service;

import com.ates.rating.app.security.payload.request.SignupRequest;
import com.ates.rating.app.viewmodel.AuthResponse;
import com.ates.rating.app.viewmodel.UserInfoVM;

public interface CustomUserDetailsService {

    AuthResponse register(UserInfoVM userInfoVM);

    AuthResponse registerUser(SignupRequest signUpRequest);
}
