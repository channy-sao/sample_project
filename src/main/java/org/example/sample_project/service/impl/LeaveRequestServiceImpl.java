package org.example.sample_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sample_project.constant.enums.DayPart;
import org.example.sample_project.constant.enums.LeaveStatus;
import org.example.sample_project.dto.request.CreateLeaveRequest;
import org.example.sample_project.dto.request.ReviewLeaveRequest;
import org.example.sample_project.dto.response.LeaveResponse;
import org.example.sample_project.entity.Employee;
import org.example.sample_project.entity.LeaveEntitlement;
import org.example.sample_project.entity.LeaveRequest;
import org.example.sample_project.entity.LeaveType;
import org.example.sample_project.exception.BadRequestException;
import org.example.sample_project.exception.ResourceNotFoundException;
import org.example.sample_project.mapper.LeaveMapper;
import org.example.sample_project.repository.EmployeeRepository;
import org.example.sample_project.repository.LeaveEntitlementRepository;
import org.example.sample_project.repository.LeaveRequestRepository;
import org.example.sample_project.repository.LeaveTypeRepository;
import org.example.sample_project.service.LeaveRequestService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class LeaveRequestServiceImpl implements LeaveRequestService {

  private final LeaveRequestRepository leaveRequestRepository;
  private final LeaveTypeRepository leaveTypeRepository;
  private final LeaveEntitlementRepository leaveEntitlementRepository;
  private final EmployeeRepository employeeRepository;

  @Override
  public LeaveResponse create(CreateLeaveRequest request) {

    Employee employee = getCurrentEmployee();

    // 1. Validate date
    if (request.getEndDate().isBefore(request.getStartDate())) {

      throw new BadRequestException("End date cannot be before start date");
    }

    // 2. Keep request inside one year for simple version
    if (request.getStartDate().getYear() != request.getEndDate().getYear()) {

      throw new BadRequestException("Leave request must be within the same year");
    }

    // 3. Find Leave Type
    LeaveType leaveType =
        leaveTypeRepository
            .findById(request.getLeaveTypeId())
            .orElseThrow(() -> new ResourceNotFoundException("Leave type not found"));

    if (!leaveType.isActive()) {
      throw new BadRequestException("Leave type is inactive");
    }

    // 4. Validate DayPart
    validateDayPart(request);

    // 5. Calculate total days
    BigDecimal totalDays = calculateTotalDays(request);

    // 6. Find entitlement
    int year = request.getStartDate().getYear();

    LeaveEntitlement entitlement =
        leaveEntitlementRepository
            .findByEmployeeIdAndLeaveTypeIdAndYear(employee.getId(), leaveType.getId(), year)
            .orElseThrow(
                () -> new BadRequestException("Leave entitlement not found for this year"));

    // 7. Calculate available balance
    BigDecimal availableDays =
        calculateAvailableDays(employee, entitlement, request.getStartDate());

    // 8. Check balance
    if (totalDays.compareTo(availableDays) > 0) {

      throw new BadRequestException(
          "Insufficient leave balance. Available: " + availableDays + " days");
    }

    // 9. Check overlapping leave
    boolean hasOverlap =
        leaveRequestRepository
            .existsByEmployeeIdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                employee.getId(),
                LeaveStatus.APPROVED,
                request.getEndDate(),
                request.getStartDate());

    if (hasOverlap) {
      throw new BadRequestException("You already have approved leave during this period");
    }

    // 10. Create request
    LeaveRequest leaveRequest =
        LeaveRequest.builder()
            .employee(employee)
            .leaveType(leaveType)
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            .dayPart(request.getDayPart())
            .totalDays(totalDays)
            .reason(request.getReason())
            .status(LeaveStatus.PENDING)
            .build();

    return LeaveMapper.toResponse(leaveRequestRepository.save(leaveRequest));
  }

  @Override
  @Transactional(readOnly = true)
  public LeaveResponse findById(Long id) {

    LeaveRequest leaveRequest =
        leaveRequestRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));
    return LeaveMapper.toResponse(leaveRequest);
  }

  private LeaveRequest findByLeaveId(Long id) {
    return leaveRequestRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Leave request not found"));
  }

  @Override
  @Transactional(readOnly = true)
  public List<LeaveResponse> findMyRequests() {

    Employee employee = getCurrentEmployee();

    return leaveRequestRepository.findByEmployeeIdOrderByStartDateDesc(employee.getId()).stream()
        .map(LeaveMapper::toResponse)
        .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<LeaveResponse> findPendingRequests() {

    return leaveRequestRepository.findByStatusOrderByStartDateAsc(LeaveStatus.PENDING).stream()
        .map(LeaveMapper::toResponse)
        .toList();
  }

  @Override
  public LeaveResponse review(Long id, ReviewLeaveRequest request) {

    Employee approver = getCurrentEmployee();

    LeaveRequest leaveRequest = findByLeaveId(id);

    // Only PENDING can be reviewed
    if (leaveRequest.getStatus() != LeaveStatus.PENDING) {

      throw new BadRequestException("Only pending leave requests can be reviewed");
    }

    Employee employee = leaveRequest.getEmployee();

    // Manager check
    if (employee.getLineManager() == null) {

      throw new BadRequestException("Employee does not have a line manager");
    }

    if (!employee.getLineManager().getId().equals(approver.getId())) {

      throw new BadRequestException("You are not the line manager of this employee");
    }

    // APPROVED
    if (request.getStatus() == LeaveStatus.APPROVED) {

      approveLeave(leaveRequest);
    }

    // REJECTED
    else if (request.getStatus() == LeaveStatus.REJECTED) {

      leaveRequest.setStatus(LeaveStatus.REJECTED);
    } else {
      throw new BadRequestException("Review status must be APPROVED or REJECTED");
    }

    leaveRequest.setApprover(approver);
    leaveRequest.setApprovedAt(java.time.LocalDateTime.now());
    leaveRequest.setApproverComment(request.getComment());

    return LeaveMapper.toResponse(leaveRequestRepository.save(leaveRequest));
  }

  private void approveLeave(LeaveRequest leaveRequest) {

    LeaveEntitlement entitlement =
        leaveEntitlementRepository
            .findByEmployeeIdAndLeaveTypeIdAndYear(
                leaveRequest.getEmployee().getId(),
                leaveRequest.getLeaveType().getId(),
                leaveRequest.getStartDate().getYear())
            .orElseThrow(() -> new ResourceNotFoundException("Leave entitlement not found"));

    BigDecimal availableDays =
        calculateAvailableDays(
            leaveRequest.getEmployee(), entitlement, leaveRequest.getStartDate());

    if (leaveRequest.getTotalDays().compareTo(availableDays) > 0) {

      throw new BadRequestException("Insufficient leave balance");
    }

    // Deduct leave
    BigDecimal newUsedDays = entitlement.getUsedDays().add(leaveRequest.getTotalDays());

    entitlement.setUsedDays(newUsedDays);

    leaveEntitlementRepository.save(entitlement);

    leaveRequest.setStatus(LeaveStatus.APPROVED);
  }

  private BigDecimal calculateTotalDays(CreateLeaveRequest request) {

    // Single day
    if (request.getStartDate().equals(request.getEndDate())) {

      if (request.getDayPart() == DayPart.FULL_DAY) {

        return BigDecimal.ONE;
      }

      return new BigDecimal("0.5");
    }

    // Multi-day
    long days =
        java.time.temporal.ChronoUnit.DAYS.between(request.getStartDate(), request.getEndDate())
            + 1;

    // Simple version:
    // multi-day leave must be FULL_DAY
    if (request.getDayPart() != DayPart.FULL_DAY) {

      throw new BadRequestException("Half-day can only be used for a single day");
    }

    return BigDecimal.valueOf(days);
  }

  private void validateDayPart(CreateLeaveRequest request) {

    boolean singleDay = request.getStartDate().equals(request.getEndDate());

    if (!singleDay && request.getDayPart() != DayPart.FULL_DAY) {

      throw new BadRequestException("Morning or afternoon leave is only allowed for one day");
    }
  }

  private BigDecimal calculateAvailableDays(
      Employee employee, LeaveEntitlement entitlement, LocalDate asOfDate) {

    BigDecimal entitledDays = entitlement.getEntitledDays();

    BigDecimal usedDays = entitlement.getUsedDays();

    /*
     * Annual Leave
     *
     * Example:
     * Entitled = 18 days/year
     *
     * Monthly rate:
     * 18 / 12 = 1.5 days/month
     */
    if (entitlement.getLeaveType().getName().equalsIgnoreCase("Annual Leave")) {

      int year = entitlement.getYear();

      // 1. Calculate completed months
      int completedMonths = calculateCompletedMonths(employee.getJoinDate(), year, asOfDate);

      // 2. Calculate monthly entitlement
      BigDecimal monthlyDays =
          entitledDays.divide(BigDecimal.valueOf(12), 2, java.math.RoundingMode.HALF_UP);

      // 3. Calculate accumulated AL
      BigDecimal accumulatedDays = monthlyDays.multiply(BigDecimal.valueOf(completedMonths));

      // 4. Never exceed yearly entitlement
      if (accumulatedDays.compareTo(entitledDays) > 0) {
        accumulatedDays = entitledDays;
      }

      // 5. Subtract used AL
      BigDecimal availableDays = accumulatedDays.subtract(usedDays);

      // 6. Never return negative balance
      if (availableDays.compareTo(BigDecimal.ZERO) < 0) {
        return BigDecimal.ZERO;
      }

      return availableDays;
    }

    /*
     * Other leave types
     *
     * Example:
     * Sick Leave = 10 days
     *
     * For now, use normal entitlement - used.
     */
    BigDecimal availableDays = entitledDays.subtract(usedDays);

    if (availableDays.compareTo(BigDecimal.ZERO) < 0) {
      return BigDecimal.ZERO;
    }

    return availableDays;
  }

  private int calculateCompletedMonths(LocalDate joinDate, int year, LocalDate asOfDate) {

    // Employee has not joined yet
    if (asOfDate.isBefore(joinDate)) {
      return 0;
    }

    // Employee joined after this entitlement year
    if (joinDate.getYear() > year) {
      return 0;
    }

    LocalDate startDate;

    /*
     * If employee joined during this year,
     * start counting from join date.
     *
     * Example:
     * Join Date = 15 Apr 2026
     * Year      = 2026
     *
     * Start = 15 Apr 2026
     */
    if (joinDate.getYear() == year) {
      startDate = joinDate;
    }

    /*
     * If employee joined before this year,
     * start counting from January 1.
     *
     * Example:
     * Join Date = 15 Apr 2024
     * Year      = 2026
     *
     * Start = 1 Jan 2026
     */
    else {
      startDate = LocalDate.of(year, 1, 1);
    }

    // Don't calculate beyond the requested date
    LocalDate endDate = asOfDate;

    LocalDate yearEnd = LocalDate.of(year, 12, 31);

    if (endDate.isAfter(yearEnd)) {
      endDate = yearEnd;
    }

    if (endDate.isBefore(startDate)) {
      return 0;
    }

    Period period = Period.between(startDate, endDate);

    return period.getYears() * 12 + period.getMonths();
  }

  private Employee getCurrentEmployee() {

    String username =
        Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();

    return employeeRepository
        .findByEmail(username)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for current user"));
  }
}
