package org.example.sample_project.mapper;

import org.example.sample_project.dto.response.ProfileResponse;
import org.example.sample_project.entity.Profile;

public class ProfileMapper {
  public static ProfileResponse toResponse(Profile profile) {
    return ProfileResponse.builder()
        .id(profile.getId())
        .phoneNumber(profile.getPhoneNumber())
        .address(profile.getAddress())
        .dateOfBirth(profile.getDateOfBirth())
        .emergencyContactName(profile.getEmergencyContactName())
        .emergencyContactPhone(profile.getEmergencyContactPhone())
        .avatarUrl(profile.getAvatarUrl())
        .build();
  }
}
