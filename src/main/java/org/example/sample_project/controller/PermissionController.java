package org.example.sample_project.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.sample_project.dto.response.BaseBodyResponse;
import org.example.sample_project.dto.response.PermissionResponse;
import org.example.sample_project.mapper.PermissionMapper;
import org.example.sample_project.repository.PermissionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/permissions")
@RequiredArgsConstructor
@Tag(name = "Permission Management", description = "For Admin Manage Permission")
public class PermissionController {
  private final PermissionRepository permissionRepository;

  @GetMapping
  public ResponseEntity<BaseBodyResponse<List<PermissionResponse>>> getAllPermissions() {
    return BaseBodyResponse.success(
        permissionRepository.findAll().stream().map(PermissionMapper::toResponse).toList(),
        "Success");
  }
}
