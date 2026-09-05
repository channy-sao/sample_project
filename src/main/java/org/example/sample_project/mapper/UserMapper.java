package org.example.sample_project.mapper;

import org.example.sample_project.dto.response.UserResponse;
import org.example.sample_project.entity.User;

import java.util.stream.Collectors;

public class UserMapper {
  public static UserResponse toResponse(User user) {
    var roles =
        user.getRoles().stream().map(RoleMapper::toRoleResponse).collect(Collectors.toSet());
    return UserResponse.builder()
        .id(user.getId())
        .email(user.getEmail())
        .enabled(user.isEnabled())
        .lastLoginAt(user.getLastLoginAt())
        .username(user.getUsername())
        .roles(roles)
        .build();
  }
}
