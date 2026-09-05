package org.example.sample_project.controller;

import lombok.RequiredArgsConstructor;
import org.example.sample_project.constant.enums.ContractType;
import org.example.sample_project.dto.response.BaseBodyResponse;
import org.example.sample_project.service.ContractService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contract")
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;
    @GetMapping("/types")
    public ResponseEntity<BaseBodyResponse<List<ContractType>>> getContractTypes(){
        return BaseBodyResponse.success(contractService.getContractTypes(), "Success");
    }
}
