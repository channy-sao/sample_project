package org.example.sample_project.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionRequest {
  private String code;
  private String name;
  private Long departmentId;
}
