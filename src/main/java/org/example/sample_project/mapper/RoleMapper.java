package org.example.sample_project.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import org.example.sample_project.dto.request.RoleRequest;
import org.example.sample_project.dto.response.PermissionResponse;
import org.example.sample_project.dto.response.RoleResponse;
import org.example.sample_project.entity.Permission;
import org.example.sample_project.entity.Role;

public class RoleMapper {
  public static RoleResponse toRoleResponse(Role role) {
    return RoleResponse.builder()
        .id(role.getId())
        .name(role.getName())
        .description(role.getDescription())
        .isActive(role.isActive())
        .permissions(getPermissionResponse(role.getPermissions()))
        .build();
  }

  public static Role toRoleEntity(RoleRequest roleRequest) {
    return Role.builder()
        .name(roleRequest.getName())
        .isActive(true)
        .description(roleRequest.getDescription())
        .build();
  }

  private static Set<PermissionResponse> getPermissionResponse(Set<Permission> permissions) {
    return permissions.stream().map(PermissionMapper::toResponse).collect(Collectors.toSet());
  }
}
