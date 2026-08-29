package org.example.sample_project.mapper;

import org.example.sample_project.dto.response.PositionResponse;
import org.example.sample_project.entity.Position;

public class PositionMapper {
  public static PositionResponse toResponse(Position position) {
    return PositionResponse.builder()
        .id(position.getId())
        .code(position.getCode())
        .name(position.getName())
        .department(DepartmentMapper.toResponse(position.getDepartment()))
        .build();
  }
}
