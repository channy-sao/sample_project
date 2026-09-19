package org.example.sample_project.dto.response;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sample_project.entity.LeaveType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveEntitlementResponse {
  private Long id;
  private EmployeeResponse employee;
  private LeaveType leaveType;
  private Integer year;
  private BigDecimal entitledDays;
  private BigDecimal usedDays = BigDecimal.ZERO;
}
