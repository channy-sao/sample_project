package org.example.sample_project.service;

import org.example.sample_project.dto.request.PositionRequest;
import org.example.sample_project.dto.response.PositionResponse;

import java.util.List;

public interface PositionService {
    PositionResponse create(PositionRequest request);
    PositionResponse update(PositionRequest request, Long id);
    PositionResponse getByCode(String code);
    PositionResponse getById(Long id);
    void deleteById(Long id);
    List<PositionResponse> filter(String filter);
}
