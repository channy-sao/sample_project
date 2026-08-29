package org.example.sample_project.mapper;

import org.example.sample_project.dto.request.DepartmentRequest;
import org.example.sample_project.dto.response.DepartmentResponse;
import org.example.sample_project.entity.Department;

public class DepartmentMapper {
  public static DepartmentResponse toResponse(Department department) {
    return DepartmentResponse.builder()
        .id(department.getId())
        .name(department.getName())
        .code(department.getCode())
        .build();
  }

  public static Department toEntity(DepartmentRequest departmentRequest){
      return Department.builder()
              .code(departmentRequest.getCode())
              .name(departmentRequest.getName())
              .build();
  }
}
