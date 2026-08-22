package org.example.sample_project.mapper;

import org.example.sample_project.dto.response.PermissionResponse;
import org.example.sample_project.entity.Permission;

public class PermissionMapper {
    public static PermissionResponse toResponse (Permission permission){
    return PermissionResponse.builder().id(permission.getId())
            .name(permission.getName().getName())
            .description(permission.getDescription())
            .category(permission.getCategory())
            .build();
    }

}
