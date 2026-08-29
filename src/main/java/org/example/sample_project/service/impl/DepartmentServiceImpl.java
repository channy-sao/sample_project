package org.example.sample_project.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sample_project.dto.request.DepartmentRequest;
import org.example.sample_project.dto.response.DepartmentResponse;
import org.example.sample_project.entity.Department;
import org.example.sample_project.exception.ResourceNotFoundException;
import org.example.sample_project.mapper.DepartmentMapper;
import org.example.sample_project.repository.DepartmentRepository;
import org.example.sample_project.service.DepartmentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
  private final DepartmentRepository departmentRepository;

  @Override
  public DepartmentResponse create(DepartmentRequest request) {
    Department department = DepartmentMapper.toEntity(request);
    Department save = departmentRepository.save(department);
    return DepartmentMapper.toResponse(save);
  }

  @Override
  public DepartmentResponse update(DepartmentRequest updateRequest, Long id) {
    Department department = getDepartmentById(id);
    department.setCode(updateRequest.getCode());
    department.setName(updateRequest.getName());
    Department updated = departmentRepository.save(department);

    return DepartmentMapper.toResponse(updated);
  }

  @Override
  public DepartmentResponse getByCode(String code) {
    Department department =
        departmentRepository
            .findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("Department by code not found"));
    return DepartmentMapper.toResponse(department);
  }

  @Override
  public DepartmentResponse getByName(String name) {
    Department department =
        departmentRepository
            .findByName(name)
            .orElseThrow(() -> new ResourceNotFoundException("Department by name not found"));
    return DepartmentMapper.toResponse(department);
  }

  @Override
  public DepartmentResponse getById(Long id) {
    log.info("start get department by id");
    return DepartmentMapper.toResponse(getDepartmentById(id));
  }

  @Override
  public void deleteById(Long id) {
    departmentRepository.deleteById(id);
    log.info("Department deleted successfully");
  }

  @Override
  public List<DepartmentResponse> filter(String name) {
    if(!StringUtils.hasText(name)){
      return departmentRepository.findAll().stream()
              .map(DepartmentMapper::toResponse)
              .toList();
    }
    return departmentRepository.findByNameLikeIgnoreCaseOrderByNameAsc("%" + name + "%").stream()
        .map(DepartmentMapper::toResponse)
        .toList();
  }

  private Department getDepartmentById(Long id) {
    return departmentRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Department", id));
  }
}
