package org.example.sample_project.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateLeaveEntitlementRequest {

  @NotNull private Long employeeId;

  @NotNull private Long leaveTypeId;

  @NotNull private Integer year;

  @NotNull
  @DecimalMin(value = "0.0")
  private BigDecimal entitledDays;
}
