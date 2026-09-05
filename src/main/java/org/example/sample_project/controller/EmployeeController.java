package org.example.sample_project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.sample_project.dto.request.EmployeeCreateRequest;
import org.example.sample_project.dto.response.BaseBodyResponse;
import org.example.sample_project.dto.response.EmployeeResponse;
import org.example.sample_project.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
  private final EmployeeService employeeService;

  @PostMapping
  public ResponseEntity<BaseBodyResponse<EmployeeResponse>> createEmployee(
      @RequestBody @Valid EmployeeCreateRequest employeeCreateRequest) {

    return BaseBodyResponse.success(
        employeeService.createEmployee(employeeCreateRequest), "Success");
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<EmployeeResponse>> getByEmployeeId(
      @PathVariable(value = "id") Long employeeId) {
    return BaseBodyResponse.success(employeeService.getByEmployeeId(employeeId), "Success");
  }
}
