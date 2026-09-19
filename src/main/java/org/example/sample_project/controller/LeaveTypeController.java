package org.example.sample_project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.sample_project.dto.request.CreateLeaveTypeRequest;
import org.example.sample_project.dto.response.BaseBodyResponse;
import org.example.sample_project.entity.LeaveType;
import org.example.sample_project.service.LeaveTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leave-types")
@RequiredArgsConstructor
public class LeaveTypeController {

  private final LeaveTypeService leaveTypeService;

  @PostMapping
  public ResponseEntity<BaseBodyResponse<LeaveType>> create(
      @Valid @RequestBody CreateLeaveTypeRequest request) {

    return BaseBodyResponse.success(leaveTypeService.create(request), "Success");
  }

  @GetMapping
  public ResponseEntity<BaseBodyResponse<List<LeaveType>>> findAll() {
    return BaseBodyResponse.success(leaveTypeService.findAll(), "Success");
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<LeaveType>> findById(@PathVariable Long id) {

    return BaseBodyResponse.success(leaveTypeService.findById(id), "Success");
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<LeaveType>> update(
      @PathVariable Long id, @Valid @RequestBody CreateLeaveTypeRequest request) {

    return BaseBodyResponse.success(leaveTypeService.update(id, request), "Success");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<Void>> delete(@PathVariable Long id) {

    leaveTypeService.delete(id);

    return BaseBodyResponse.success("Success");
  }
}
