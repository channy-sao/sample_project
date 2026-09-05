package org.example.sample_project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserResponse {
  private Long id;
  private String username;
  private String email;
  private boolean enabled;
  private LocalDateTime lastLoginAt;
  private Set<RoleResponse> roles;
}
