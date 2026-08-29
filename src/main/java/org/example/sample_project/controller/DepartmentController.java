package org.example.sample_project.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.sample_project.dto.request.DepartmentRequest;
import org.example.sample_project.dto.response.BaseBodyResponse;
import org.example.sample_project.dto.response.DepartmentResponse;
import org.example.sample_project.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/departments")
@RequiredArgsConstructor
@Tag(name = "Department", description = "Department Management")
public class DepartmentController {
  private final DepartmentService departmentService;

  @PostMapping
  public ResponseEntity<BaseBodyResponse<DepartmentResponse>> create(
      @RequestBody @Valid DepartmentRequest request) {
    return BaseBodyResponse.success(
        departmentService.create(request), "Department Created Successfully");
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<DepartmentResponse>> update(
      @RequestBody @Valid DepartmentRequest request, @PathVariable Long id) {
    return BaseBodyResponse.success(
        departmentService.update(request, id), "Department Created Successfully");
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<DepartmentResponse>> getById(@PathVariable Long id) {
    return BaseBodyResponse.success(
        departmentService.getById(id), "Get Department by id Successfully");
  }

  @GetMapping("/code/{code}")
  public ResponseEntity<BaseBodyResponse<DepartmentResponse>> getByCode(@PathVariable String code) {
    return BaseBodyResponse.success(
        departmentService.getByCode(code), "Get Department by code Successfully");
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<BaseBodyResponse<DepartmentResponse>> getByName(@PathVariable String name) {
    return BaseBodyResponse.success(
        departmentService.getByName(name), "Get Department by name Successfully");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<Void>> deleteById(@PathVariable Long id) {
    departmentService.deleteById(id);
    return BaseBodyResponse.success("Delete Department by id Successfully");
  }

  @GetMapping("/filter")
  public ResponseEntity<BaseBodyResponse<List<DepartmentResponse>>> filterDepartment(
      @RequestParam(value = "", name = "name", required = false) String name) {
    return BaseBodyResponse.success(
        departmentService.filter(name), "Filter Department by name Successfully");
  }
}
