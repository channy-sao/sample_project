package org.example.sample_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.sample_project.constant.enums.DayPart;
import org.example.sample_project.constant.enums.LeaveStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "leave_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "employee_id", nullable = false)
  private Employee employee;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "leave_type_id", nullable = false)
  private LeaveType leaveType;

  @Column(nullable = false)
  private LocalDate startDate;

  @Column(nullable = false)
  private LocalDate endDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 20)
  private DayPart dayPart;

  @Column(nullable = false, precision = 5, scale = 1)
  private BigDecimal totalDays;

  @Column(length = 500)
  private String reason;

  @Enumerated(EnumType.STRING)
  @Builder.Default
  @Column(nullable = false, length = 20)
  private LeaveStatus status = LeaveStatus.PENDING;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "approver_id")
  private Employee approver;

  private LocalDateTime approvedAt;

  @Column(length = 500)
  private String approverComment;
}