package org.example.sample_project.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeUpdateRequest {

  @NotBlank private String firstName;

  @NotBlank private String lastName;

  @NotNull private LocalDate joinDate;

  @NotNull private Long departmentId;

  @NotNull private Long positionId;

  private Long lineManagerId;

  @Valid private ProfileRequest profile;

  @Valid private ContractRequest contract;

}
