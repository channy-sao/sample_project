package org.example.sample_project.service;

import org.example.sample_project.dto.request.RoleRequest;
import org.example.sample_project.dto.response.RoleResponse;
import org.springframework.data.domain.Page;


import java.util.List;

public interface RoleService {
    RoleResponse createRole(RoleRequest roleRequest);
    RoleResponse updateRole(Long id, RoleRequest updateRequest);
    RoleResponse getByRoleId(Long roleId);
    List<RoleResponse> getRoleList();
    Page<RoleResponse> filter(Integer page, Integer  pageSize, String filter);
    void deleteRoleById (Long roleId);
    void toggleRoleStatus(Long roleId);
}
