package org.example.sample_project.utils;

import lombok.NoArgsConstructor;
import org.example.sample_project.constant.message.ResponseMessageConstant;
import org.example.sample_project.dto.response.StatusResponse;
import org.springframework.http.HttpStatusCode;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class StatusResponseUtils {
  public static StatusResponse createStatusResponse(HttpStatusCode status, String message) {
    return new StatusResponse(
        (short) status.value(), message != null ? message : ResponseMessageConstant.FAILED);
  }
}