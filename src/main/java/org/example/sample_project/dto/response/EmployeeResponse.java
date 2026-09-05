package org.example.sample_project.dto.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sample_project.dto.request.ContractRequest;
import org.example.sample_project.dto.request.ProfileRequest;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String employeeCode;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate joinDate;
    private DepartmentResponse department;
    private PositionResponse position;
    private EmployeeResponse lineManager;
    private ProfileResponse profile;
    private ContractResponse contract;
    private UserResponse user;
}
