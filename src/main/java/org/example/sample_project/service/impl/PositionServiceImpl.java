package org.example.sample_project.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sample_project.dto.request.PositionRequest;
import org.example.sample_project.dto.response.PositionResponse;
import org.example.sample_project.entity.Department;
import org.example.sample_project.entity.Position;
import org.example.sample_project.exception.ResourceNotFoundException;
import org.example.sample_project.mapper.PositionMapper;
import org.example.sample_project.repository.DepartmentRepository;
import org.example.sample_project.repository.PositionRepository;
import org.example.sample_project.service.PositionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PositionServiceImpl implements PositionService {
  private final PositionRepository positionRepository;
  private final DepartmentRepository departmentRepository;

  @Override
  public PositionResponse create(PositionRequest request) {
    Department department =
        departmentRepository
            .findById(request.getDepartmentId())
            .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

    Position position =
        Position.builder()
            .code(request.getCode())
            .name(request.getName())
            .department(department)
            .build();
    position = positionRepository.save(position);
    return PositionMapper.toResponse(position);
  }

  @Override
  public PositionResponse update(PositionRequest request, Long id) {
    Position position = getPositionById(id);
    Department department =
        departmentRepository
            .findById(request.getDepartmentId())
            .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

    position.setCode(request.getCode());
    position.setName(request.getName());
    position.setDepartment(department);
    position = positionRepository.save(position);
    return PositionMapper.toResponse(position);
  }

  @Override
  public PositionResponse getByCode(String code) {
    Position position =
        positionRepository
            .findByCode(code)
            .orElseThrow(() -> new ResourceNotFoundException("Position Not found"));
    return PositionMapper.toResponse(position);
  }

  @Override
  public PositionResponse getById(Long id) {
    return PositionMapper.toResponse(getPositionById(id));
  }

  @Override
  public void deleteById(Long id) {
    positionRepository.deleteById(id);
  }

  @Override
  public List<PositionResponse> filter(String filter) {
    if (!StringUtils.hasText(filter)) {
      return positionRepository.findAll().stream().map(PositionMapper::toResponse).toList();
    }
    return positionRepository.findByNameLikeIgnoreCaseOrderByNameAsc("%" + filter + "%").stream()
        .map(PositionMapper::toResponse)
        .toList();
  }

  private Position getPositionById(Long id) {
    return positionRepository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Position", id));
  }
}
