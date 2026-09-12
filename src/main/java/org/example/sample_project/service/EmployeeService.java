package org.example.sample_project.service;

import org.example.sample_project.dto.request.EmployeeCreateRequest;
import org.example.sample_project.dto.request.EmployeeUpdateRequest;
import org.example.sample_project.dto.response.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface EmployeeService {
    EmployeeResponse createEmployee (EmployeeCreateRequest employeeCreateRequest);
    EmployeeResponse getByEmployeeId(Long employeeId);
    EmployeeResponse update(Long id, EmployeeUpdateRequest updateRequest);
    void delete (Long id);
    void updateStatus(Long id);

    Page<EmployeeResponse> getEmployees(int page, int size, String filter);

    String uploadFile (MultipartFile file , Long id);
}
