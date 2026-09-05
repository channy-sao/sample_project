package org.example.sample_project.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.sample_project.constant.enums.ContractType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContractResponse {
  private Long id;
  private String contractNumber;
  private ContractType contractType;
  private LocalDate startDate;
  private LocalDate endDate;
  private BigDecimal baseSalary;
}
