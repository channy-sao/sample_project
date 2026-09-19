package org.example.sample_project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.sample_project.dto.request.CreateLeaveEntitlementRequest;
import org.example.sample_project.dto.response.BaseBodyResponse;
import org.example.sample_project.dto.response.LeaveEntitlementResponse;
import org.example.sample_project.entity.LeaveEntitlement;
import org.example.sample_project.service.LeaveEntitlementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leave-entitlements")
@RequiredArgsConstructor
public class LeaveEntitlementController {

  private final LeaveEntitlementService leaveEntitlementService;

  @PostMapping
  public ResponseEntity<BaseBodyResponse<LeaveEntitlementResponse>> create(
      @Valid @RequestBody CreateLeaveEntitlementRequest request) {

    return BaseBodyResponse.success(leaveEntitlementService.create(request), "Success");
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<LeaveEntitlementResponse>> findById(
      @PathVariable Long id) {

    return BaseBodyResponse.success(leaveEntitlementService.findById(id), "Success");
  }

  @GetMapping("/employee/{employeeId}")
  public ResponseEntity<BaseBodyResponse<List<LeaveEntitlementResponse>>> findByEmployee(
      @PathVariable Long employeeId, @RequestParam Integer year) {

    return BaseBodyResponse.success(
        leaveEntitlementService.findByEmployee(employeeId, year), "Success");
  }

  @GetMapping("/my")
  public ResponseEntity<BaseBodyResponse<List<LeaveEntitlementResponse>>> findMyEntitlements(
      @RequestParam Integer year) {

    return BaseBodyResponse.success(leaveEntitlementService.findMyEntitlements(year), "Success");
  }

  @GetMapping("/my/{leaveTypeId}")
  public ResponseEntity<BaseBodyResponse<LeaveEntitlementResponse>> findMyEntitlement(
      @PathVariable Long leaveTypeId, @RequestParam Integer year) {

    return BaseBodyResponse.success(
        leaveEntitlementService.findMyEntitlement(leaveTypeId, year), "Success");
  }

  @GetMapping("/employee/{employeeId}/available")
  public ResponseEntity<BaseBodyResponse<Double>> getAvailableDays(
      @PathVariable Long employeeId, @RequestParam Long leaveTypeId, @RequestParam Integer year) {

    return BaseBodyResponse.success(
        leaveEntitlementService.getAvailableDays(employeeId, leaveTypeId, year), "Success");
  }
}
