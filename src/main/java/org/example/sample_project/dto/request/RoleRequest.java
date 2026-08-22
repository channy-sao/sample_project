package org.example.sample_project.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {
    @NotBlank(message = "Role name must be not blank")
    private String name;
    private String description;
    private Set<Long> permissionIds;
}
