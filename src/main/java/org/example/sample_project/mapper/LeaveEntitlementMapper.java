package org.example.sample_project.mapper;

import lombok.NoArgsConstructor;
import org.example.sample_project.dto.response.LeaveEntitlementResponse;
import org.example.sample_project.entity.LeaveEntitlement;

@NoArgsConstructor
public final class LeaveEntitlementMapper {
  public static LeaveEntitlementResponse toResponse(LeaveEntitlement leaveEntitlement) {
    return LeaveEntitlementResponse.builder()
        .id(leaveEntitlement.getId())
        .employee(EmployeeMapper.toResponse(leaveEntitlement.getEmployee()))
        .entitledDays(leaveEntitlement.getEntitledDays())
        .usedDays(leaveEntitlement.getUsedDays())
        .leaveType(leaveEntitlement.getLeaveType())
        .year(leaveEntitlement.getYear())
        .build();
  }
}
