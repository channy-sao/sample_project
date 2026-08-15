package org.example.sample_project.exception;

import org.springframework.security.access.AccessDeniedException;

public class ForbiddenException extends AccessDeniedException {
  public ForbiddenException(String message) {
    super(message);
  }
}
