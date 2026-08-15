package org.example.sample_project.service;

import org.example.sample_project.dto.request.LoginRequest;
import org.example.sample_project.dto.response.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(LoginRequest loginRequest);
}
