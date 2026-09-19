package org.example.sample_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sample_project.dto.request.CreateLeaveEntitlementRequest;
import org.example.sample_project.dto.response.LeaveEntitlementResponse;
import org.example.sample_project.entity.Employee;
import org.example.sample_project.entity.LeaveEntitlement;
import org.example.sample_project.entity.LeaveType;
import org.example.sample_project.mapper.LeaveEntitlementMapper;
import org.example.sample_project.repository.EmployeeRepository;
import org.example.sample_project.repository.LeaveEntitlementRepository;
import org.example.sample_project.repository.LeaveTypeRepository;
import org.example.sample_project.service.LeaveEntitlementService;
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
public class LeaveEntitlementServiceImpl implements LeaveEntitlementService {

  private final LeaveEntitlementRepository leaveEntitlementRepository;
  private final EmployeeRepository employeeRepository;
  private final LeaveTypeRepository leaveTypeRepository;

  @Override
  public LeaveEntitlementResponse create(CreateLeaveEntitlementRequest request) {

    Employee employee =
        employeeRepository
            .findById(request.getEmployeeId())
            .orElseThrow(() -> new RuntimeException("Employee not found"));

    LeaveType leaveType =
        leaveTypeRepository
            .findById(request.getLeaveTypeId())
            .orElseThrow(() -> new RuntimeException("Leave type not found"));

    if (leaveEntitlementRepository
        .findByEmployeeIdAndLeaveTypeIdAndYear(
            request.getEmployeeId(), request.getLeaveTypeId(), request.getYear())
        .isPresent()) {

      throw new RuntimeException(
          "Leave entitlement already exists for this employee, leave type and year");
    }

    LeaveEntitlement entitlement =
        LeaveEntitlement.builder()
            .employee(employee)
            .leaveType(leaveType)
            .year(request.getYear())
            .entitledDays(request.getEntitledDays())
            .usedDays(BigDecimal.ZERO)
            .build();

    return LeaveEntitlementMapper.toResponse(leaveEntitlementRepository.save(entitlement));
  }

  @Override
  @Transactional(readOnly = true)
  public LeaveEntitlementResponse findById(Long id) {

    LeaveEntitlement leaveEntitlement =
        leaveEntitlementRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Leave entitlement not found"));
    return LeaveEntitlementMapper.toResponse(leaveEntitlement);
  }

  @Override
  @Transactional(readOnly = true)
  public List<LeaveEntitlementResponse> findByEmployee(Long employeeId, Integer year) {

    return leaveEntitlementRepository.findByEmployeeIdAndYear(employeeId, year).stream()
        .map(LeaveEntitlementMapper::toResponse)
        .toList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<LeaveEntitlementResponse> findMyEntitlements(Integer year) {

    Employee employee = getCurrentEmployee();

    return findByEmployee(employee.getId(), year);
  }

  @Override
  @Transactional(readOnly = true)
  public LeaveEntitlementResponse findMyEntitlement(Long leaveTypeId, Integer year) {

    Employee employee = getCurrentEmployee();

    LeaveEntitlement leaveEntitlement =
        leaveEntitlementRepository
            .findByEmployeeIdAndLeaveTypeIdAndYear(employee.getId(), leaveTypeId, year)
            .orElseThrow(() -> new RuntimeException("Leave entitlement not found"));
    return LeaveEntitlementMapper.toResponse(leaveEntitlement);
  }

  @Override
  @Transactional(readOnly = true)
  public double getAvailableDays(Long employeeId, Long leaveTypeId, Integer year) {

    LeaveEntitlement entitlement =
        leaveEntitlementRepository
            .findByEmployeeIdAndLeaveTypeIdAndYear(employeeId, leaveTypeId, year)
            .orElseThrow(() -> new RuntimeException("Leave entitlement not found"));

    BigDecimal entitledDays = entitlement.getEntitledDays();

    BigDecimal usedDays = entitlement.getUsedDays();

    BigDecimal available = entitledDays.subtract(usedDays);

    return available.doubleValue();
  }

  private Employee getCurrentEmployee() {

    String username =
        Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getName();

    return employeeRepository
        .findByEmail(username)
        .orElseThrow(() -> new RuntimeException("Employee not found for current user"));
  }
}
