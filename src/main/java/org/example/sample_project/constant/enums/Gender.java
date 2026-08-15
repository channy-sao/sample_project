package org.example.sample_project.constant.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum Gender {
    MALE("male"),
    FEMALE("female");

    private final String value;

    /**
     * Finds the Gender enum matching the exact text value.
     *
     * @param value The raw string (e.g., "male" or "female")
     * @return The matching Gender enum, or null if no match is found
     */
    public static Gender fromValue(String value) {
        if (value == null) {
            return null;
        }
        for (Gender gender : Gender.values()) {
            if (gender.getValue().equalsIgnoreCase(value.trim())) {
                return gender;
            }
        }
        return null;
        // Alternatively, throw an exception depending on your business rules:
        // throw new IllegalArgumentException("Unknown gender value: " + value);
    }
}
