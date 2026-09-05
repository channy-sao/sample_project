package org.example.sample_project.service.impl;

import org.example.sample_project.constant.enums.ContractType;
import org.example.sample_project.service.ContractService;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {
    @Override
    public List<ContractType> getContractTypes() {
        EnumSet<ContractType> contractTypes = EnumSet.allOf(ContractType.class);
        return contractTypes.stream().toList();
    }
}
