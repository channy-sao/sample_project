package org.example.sample_project.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import org.example.sample_project.dto.response.BaseBodyResponse;
import org.example.sample_project.utils.JsonUtils;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDenied implements AccessDeniedHandler {
  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException, ServletException {
    HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(403);

    response.setStatus(httpStatusCode.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    PrintWriter writer = response.getWriter();
    writer.print(
        JsonUtils.toJson(
            BaseBodyResponse.bodyFailed(httpStatusCode, accessDeniedException.getMessage())));
    writer.flush();
    writer.close();
  }
}
