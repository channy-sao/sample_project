package org.example.sample_project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.sample_project.dto.request.CreateLeaveRequest;
import org.example.sample_project.dto.request.ReviewLeaveRequest;
import org.example.sample_project.dto.response.BaseBodyResponse;
import org.example.sample_project.dto.response.LeaveResponse;
import org.example.sample_project.entity.LeaveRequest;
import org.example.sample_project.service.LeaveRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leave-requests")
@RequiredArgsConstructor
public class LeaveRequestController {

  private final LeaveRequestService leaveRequestService;

  @PostMapping
  public ResponseEntity<BaseBodyResponse<LeaveResponse>> create(
      @Valid @RequestBody CreateLeaveRequest request) {

    return BaseBodyResponse.success(leaveRequestService.create(request), "Success");
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<LeaveResponse>> findById(@PathVariable Long id) {

    return BaseBodyResponse.success(leaveRequestService.findById(id), "Success");
  }

  @GetMapping("/my")
  public ResponseEntity<BaseBodyResponse<List<LeaveResponse>>> findMyRequests() {

    return BaseBodyResponse.success(leaveRequestService.findMyRequests(), "Success");
  }

  @GetMapping("/pending")
  public ResponseEntity<BaseBodyResponse<List<LeaveResponse>>> findPendingRequests() {

    return BaseBodyResponse.success(leaveRequestService.findPendingRequests(), "Success");
  }

  @PatchMapping("/{id}/review")
  public ResponseEntity<BaseBodyResponse<LeaveResponse>> review(
      @PathVariable Long id, @Valid @RequestBody ReviewLeaveRequest request) {

    return BaseBodyResponse.success(leaveRequestService.review(id, request), "Success");
  }
}
