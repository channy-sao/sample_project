package org.example.sample_project.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.example.sample_project.constant.enums.DayPart;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateLeaveRequest {

  @NotNull private Long leaveTypeId;

  @NotNull private LocalDate startDate;

  @NotNull private LocalDate endDate;

  @NotNull private DayPart dayPart;

  @Size(max = 500)
  private String reason;
}
