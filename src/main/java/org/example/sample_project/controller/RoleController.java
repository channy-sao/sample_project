package org.example.sample_project.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.sample_project.dto.request.RoleRequest;
import org.example.sample_project.dto.response.BaseBodyResponse;
import org.example.sample_project.dto.response.RoleResponse;
import org.example.sample_project.service.RoleService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@Tag(name = "Role Management", description = "For Admin Manage Role")
public class RoleController {
  private final RoleService roleService;

  @GetMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<RoleResponse>> getRoleById(
      @PathVariable(value = "id") Long roleId) {
    return BaseBodyResponse.success(roleService.getByRoleId(roleId), "Get role By Id Success");
  }

  @GetMapping
  public ResponseEntity<BaseBodyResponse<List<RoleResponse>>> getRoleList() {
    return BaseBodyResponse.success(roleService.getRoleList(), "Get role List success");
  }

  @PostMapping
  public ResponseEntity<BaseBodyResponse<RoleResponse>> createRole(
      @RequestBody @Valid RoleRequest request) {
    return BaseBodyResponse.success(roleService.createRole(request), "Created Successfully");
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<RoleResponse>> updateRole(
      @RequestBody @Valid RoleRequest request, @PathVariable(value = "id") Long roleId) {
    return BaseBodyResponse.success(
        roleService.updateRole(roleId, request), "Updated Successfully");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<Void>> deleteRole(
      @PathVariable(value = "id") Long roleId) {
    roleService.deleteRoleById(roleId);
    return BaseBodyResponse.success("Delete Success");
  }

  @PatchMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<Void>> toggleStatus(
      @PathVariable(value = "id") Long roleId) {
    roleService.toggleRoleStatus(roleId);
    return BaseBodyResponse.success("Toggle Status Success");
  }

  @GetMapping("/filter")
  public ResponseEntity<BaseBodyResponse<List<RoleResponse>>> filter(
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
      @RequestParam(name = "filter", defaultValue = "") String filter) {
    return BaseBodyResponse.pageSuccess(roleService.filter(page, pageSize, filter), "Success");
  }
}
