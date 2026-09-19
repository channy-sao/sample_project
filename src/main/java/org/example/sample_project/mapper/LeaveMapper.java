package org.example.sample_project.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.sample_project.dto.response.LeaveResponse;
import org.example.sample_project.entity.LeaveRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class LeaveMapper {
  public static LeaveResponse toResponse(LeaveRequest leaveRequest) {
    return LeaveResponse.builder()
        .id(leaveRequest.getId())
        .dayPart(leaveRequest.getDayPart())
        .approvedAt(leaveRequest.getApprovedAt())
        .approver(EmployeeMapper.toResponse(leaveRequest.getApprover()))
        .approverComment(leaveRequest.getApproverComment())
        .startDate(leaveRequest.getStartDate())
        .endDate(leaveRequest.getEndDate())
        .status(leaveRequest.getStatus())
        .employee(EmployeeMapper.toResponse(leaveRequest.getEmployee()))
        .leaveType(leaveRequest.getLeaveType())
        .reason(leaveRequest.getReason())
        .totalDays(leaveRequest.getTotalDays())
        .build();
  }
}
