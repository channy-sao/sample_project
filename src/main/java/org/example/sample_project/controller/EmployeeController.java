package org.example.sample_project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.sample_project.dto.request.EmployeeCreateRequest;
import org.example.sample_project.dto.request.EmployeeUpdateRequest;
import org.example.sample_project.dto.response.BaseBodyResponse;
import org.example.sample_project.dto.response.EmployeeResponse;
import org.example.sample_project.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

  @GetMapping
  public ResponseEntity<BaseBodyResponse<List<EmployeeResponse>>> getEmployees(
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size,
      @RequestParam(name = "filter", defaultValue = "") String filter) {
    return BaseBodyResponse.pageSuccess(
        employeeService.getEmployees(page, size, filter), "Success");
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<EmployeeResponse>> updateEmployee(
      @RequestBody @Valid EmployeeUpdateRequest updateRequest, @PathVariable Long id) {

    return BaseBodyResponse.success(employeeService.update(id, updateRequest), "Success");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<Void>> delete(@PathVariable Long id) {
    employeeService.delete(id);
    return BaseBodyResponse.success("Success");
  }

  @PatchMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<Void>> updateStatus(@PathVariable Long id) {
    employeeService.updateStatus(id);
    return BaseBodyResponse.success("Success");
  }

  @PostMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<String>> uploadFile(
      @RequestPart(value = "file") MultipartFile multipartFile, @PathVariable Long id) {

    return BaseBodyResponse.success(employeeService.uploadFile(multipartFile, id), "Success");
  }
}
