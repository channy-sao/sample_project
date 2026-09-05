package org.example.sample_project.mapper;

import org.example.sample_project.dto.response.ContractResponse;
import org.example.sample_project.entity.Contract;

public class ContractMapper {
  public static ContractResponse toResponse(Contract contract) {
    return ContractResponse.builder()
        .id(contract.getId())
        .contractNumber(contract.getContractNumber())
        .contractType(contract.getContractType())
        .startDate(contract.getStartDate())
        .endDate(contract.getEndDate())
        .baseSalary(contract.getBaseSalary())
        .build();
  }
}
