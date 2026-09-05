package org.example.sample_project.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeCreateRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotNull
    private LocalDate joinDate;

    @NotNull
    private Long departmentId;

    @NotNull
    private Long positionId;

    // Optional
    private Long lineManagerId;

    @Valid
    private ProfileRequest profile;

    @Valid
    private ContractRequest contract;

//    @Valid
//    private UserCreateRequest user;
}