package org.example.sample_project.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.sample_project.constant.enums.ContractType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractRequest {

    @NotBlank
    private String contractNumber;

    @NotNull
    private ContractType contractType;

    @NotNull
    private LocalDate startDate;

    private LocalDate endDate;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal baseSalary;
}