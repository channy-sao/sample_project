package org.example.sample_project.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.sample_project.dto.request.PositionRequest;
import org.example.sample_project.dto.response.BaseBodyResponse;
import org.example.sample_project.dto.response.DepartmentResponse;
import org.example.sample_project.dto.response.PositionResponse;
import org.example.sample_project.service.PositionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/positions")
@RequiredArgsConstructor
public class PositionController {
  private final PositionService positionService;

  @PostMapping
  public ResponseEntity<BaseBodyResponse<PositionResponse>> create(
      @RequestBody @Valid PositionRequest request) {
    return BaseBodyResponse.success(
        positionService.create(request), "Create Position Successfully");
  }

  @PutMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<PositionResponse>> update(
      @RequestBody @Valid PositionRequest request, @PathVariable Long id) {
    return BaseBodyResponse.success(positionService.update(request, id), "Updated Successfully");
  }

  @GetMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<PositionResponse>> getById(@PathVariable Long id) {
    return BaseBodyResponse.success(positionService.getById(id), "Get Successfully");
  }

  @GetMapping("/code/{code}")
  public ResponseEntity<BaseBodyResponse<PositionResponse>> getByCode(@PathVariable String code) {
    return BaseBodyResponse.success(positionService.getByCode(code), "Get Successfully");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<BaseBodyResponse<Void>> deleteById(@PathVariable Long id) {
    positionService.deleteById(id);
    return BaseBodyResponse.success("Delete position by id Successfully");
  }

    @GetMapping("/filter")
    public ResponseEntity<BaseBodyResponse<List<PositionResponse>>> filter(
            @RequestParam(value = "", name = "name", required = false) String name) {
        return BaseBodyResponse.success(
                positionService.filter(name), "Filter Department by name Successfully");
    }
}
