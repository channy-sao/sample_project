package org.example.sample_project.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.sample_project.constant.enums.LeaveStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewLeaveRequest {

  @NotNull private LeaveStatus status;

  @Size(max = 500)
  private String comment;
}
