package org.example.sample_project.exception;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String msg) {
    super(msg);
  }
}
