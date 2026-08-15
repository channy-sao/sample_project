package org.example.sample_project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.sample_project.dto.request.LoginRequest;
import org.example.sample_project.dto.response.BaseBodyResponse;
import org.example.sample_project.dto.response.LoginResponse;
import org.example.sample_project.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<BaseBodyResponse<LoginResponse>> login(@RequestBody @Valid LoginRequest loginRequest){
        LoginResponse response = authenticationService.login(loginRequest);
        return BaseBodyResponse.success(response, "Login Success");
    }

}
