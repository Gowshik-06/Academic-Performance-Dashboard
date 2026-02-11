package com.apda.apda_backend.service;

import com.apda.apda_backend.dto.LoginRequest;
import com.apda.apda_backend.dto.LoginResponse;
import com.apda.apda_backend.dto.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
