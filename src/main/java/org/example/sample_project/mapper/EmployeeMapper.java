package org.example.sample_project.mapper;

import org.example.sample_project.dto.response.EmployeeResponse;
import org.example.sample_project.entity.Employee;

public class EmployeeMapper {
  public static EmployeeResponse toResponse(Employee employee) {
    return EmployeeResponse.builder()
        .id(employee.getId())
        .employeeCode(employee.getEmployeeCode())
        .firstName(employee.getFirstName())
        .lastName(employee.getLastName())
        .email(employee.getEmail())
        .joinDate(employee.getJoinDate())
        .department(DepartmentMapper.toResponse(employee.getDepartment()))
        .position(PositionMapper.toResponse(employee.getPosition()))
        .lineManager(null)
        .profile(ProfileMapper.toResponse(employee.getProfile()))
        .contract(ContractMapper.toResponse(employee.getContract()))
        .user(UserMapper.toResponse(employee.getUser()))
        .build();
  }
}
