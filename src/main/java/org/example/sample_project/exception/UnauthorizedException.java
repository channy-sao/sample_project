package org.example.sample_project.exception;

import org.springframework.security.core.AuthenticationException;

public class UnauthorizedException extends AuthenticationException {
  public UnauthorizedException(String msg) {
    super(msg);
  }
}
