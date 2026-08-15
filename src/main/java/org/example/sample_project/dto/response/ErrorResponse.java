package org.example.sample_project.dto.response;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int status;           // HTTP status code (400, 404, 500...)
    private String errorCode;     // App-specific code (NOT_FOUND, DUPLICATE...)
    private String message;       // Human-readable message
    private String path;          // Request path that caused the error
    private LocalDateTime timestamp;

    // For validation errors — field-level details
    private Map<String, String> fieldErrors;
}
