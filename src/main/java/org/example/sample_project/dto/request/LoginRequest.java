package org.example.sample_project.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotNull(message = "Email must be not null")
    private String email;
    @NotBlank(message = "Password must be not blank")
    @Size(min = 4 , message = "Password must be at least 4 digits")
    private String password;
}
