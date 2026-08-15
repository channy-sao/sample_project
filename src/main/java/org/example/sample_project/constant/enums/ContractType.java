package org.example.sample_project.constant.enums;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public enum ContractType {
    PERMANENT,
    FIXED_TERM,
    INTERNSHIP
}
