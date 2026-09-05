package org.example.sample_project.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {
  private Long id;
  private String phoneNumber;
  private String address;

  private LocalDate dateOfBirth;

  private String emergencyContactName;

  private String emergencyContactPhone;

  private String avatarUrl;
}
