package org.example.sample_project.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sample_project.constant.enums.DayPart;
import org.example.sample_project.constant.enums.LeaveStatus;
import org.example.sample_project.entity.Employee;
import org.example.sample_project.entity.LeaveType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LeaveResponse {
  private Long id;

  private EmployeeResponse employee;

  private LeaveType leaveType;

  private LocalDate startDate;

  private LocalDate endDate;

  private DayPart dayPart;

  private BigDecimal totalDays;

  private String reason;

  private LeaveStatus status = LeaveStatus.PENDING;

  private EmployeeResponse approver;

  private LocalDateTime approvedAt;

  private String approverComment;
}
