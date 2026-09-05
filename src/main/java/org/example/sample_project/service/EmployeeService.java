package org.example.sample_project.service;

import org.example.sample_project.dto.request.EmployeeCreateRequest;
import org.example.sample_project.dto.response.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse createEmployee (EmployeeCreateRequest employeeCreateRequest);
    EmployeeResponse getByEmployeeId(Long employeeId);
}
