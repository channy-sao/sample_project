package org.example.sample_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sample_project.dto.CustomUserDetails;
import org.example.sample_project.dto.request.LoginRequest;
import org.example.sample_project.dto.response.LoginResponse;
import org.example.sample_project.exception.UnauthorizedException;
import org.example.sample_project.service.AuthenticationService;
import org.example.sample_project.utils.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  @Override
  public LoginResponse login(LoginRequest loginRequest) {
    try {
      Authentication authentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                  loginRequest.getEmail(), loginRequest.getPassword()));

      CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

      String accessToken = jwtService.generateAccessToken(userDetails);
      LoginResponse loginResponse =
          LoginResponse.builder()
              .accessToken(accessToken)
              .tokenType("Bearer")
              .expiresInMs(jwtService.getExpiresInMs())
              .email(userDetails.getUsername())
              .fullName(userDetails.getFullName())
              .build();
      return loginResponse;
    } catch (Exception ex) {
      throw new UnauthorizedException("Username or Password is incorrect");
    }
  }
}
