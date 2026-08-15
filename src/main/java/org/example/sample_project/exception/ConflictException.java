package org.example.sample_project.exception;

public class ConflictException extends RuntimeException {
  public ConflictException(String msg) {
    super(msg);
  }
}