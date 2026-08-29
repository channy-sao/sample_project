package org.example.sample_project.service;

import org.example.sample_project.dto.request.DepartmentRequest;
import org.example.sample_project.dto.response.DepartmentResponse;

import java.util.List;

public interface DepartmentService {
    DepartmentResponse create(DepartmentRequest request);
    DepartmentResponse update(DepartmentRequest updateRequest, Long id);
    DepartmentResponse getByCode(String code);
    DepartmentResponse getByName(String name);
    DepartmentResponse getById(Long id);
    void deleteById(Long id);
    List<DepartmentResponse> filter(String name);
}
