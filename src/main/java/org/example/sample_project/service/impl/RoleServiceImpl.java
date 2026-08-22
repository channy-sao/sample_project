package org.example.sample_project.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sample_project.dto.request.RoleRequest;
import org.example.sample_project.dto.response.RoleResponse;
import org.example.sample_project.entity.Permission;
import org.example.sample_project.entity.Role;
import org.example.sample_project.exception.ResourceNotFoundException;
import org.example.sample_project.mapper.RoleMapper;
import org.example.sample_project.repository.PermissionRepository;
import org.example.sample_project.repository.RoleRepository;
import org.example.sample_project.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
  private final RoleRepository roleRepository;
  private final PermissionRepository permissionRepository;

  @Transactional
  @Override
  public RoleResponse createRole(RoleRequest roleRequest) {
    log.info("======= create role : {} =======", roleRequest.getName());
    Role roleEntity = RoleMapper.toRoleEntity(roleRequest);

    // fine permission
    List<Permission> permissions = permissionRepository.findAllById(roleRequest.getPermissionIds());

    roleEntity.setPermissions(new HashSet<>(permissions));

    Role save = roleRepository.save(roleEntity);

    return RoleMapper.toRoleResponse(save);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public RoleResponse updateRole(Long id, RoleRequest updateRequest) {
    log.info("===== update role : {} =====", id);
    Role existing = getById(id);

    existing.setName(updateRequest.getName());
    existing.setDescription((updateRequest.getDescription()));

    existing.getPermissions().clear();

    List<Permission> permissions =
        permissionRepository.findAllById(updateRequest.getPermissionIds());
    existing.setPermissions(new HashSet<>(permissions));

    return RoleMapper.toRoleResponse(roleRepository.save(existing));
  }

  @Transactional(readOnly = true)
  @Override
  public RoleResponse getByRoleId(Long roleId) {
    log.info("======= get role by id =========");
    Role role = getById(roleId);
    return RoleMapper.toRoleResponse(role);
  }

  @Transactional(readOnly = true)
  @Override
  public List<RoleResponse> getRoleList() {
    log.info("======== get role list ==========");
    return roleRepository.findAll().stream().map(RoleMapper::toRoleResponse).toList();
  }

  @Override
  public Page<RoleResponse> filter(Integer page, Integer pageSize, String filter) {
    log.info("===== filter as page ======");
    Pageable pageable = PageRequest.of(page - 1, pageSize);
    Page<Role> result = roleRepository.filter(filter , pageable);

    return result.map(RoleMapper::toRoleResponse);
  }

  @Transactional
  @Override
  public void deleteRoleById(Long roleId) {
    log.info("======= delete role by id : {} ========", roleId);
    roleRepository.deleteById(roleId);
    log.info("======= delete role success =====");
  }

  @Transactional
  @Override
  public void toggleRoleStatus(Long roleId) {
    log.info("===== toggle role status ======");
    Role role = getById(roleId);
    role.setActive(!role.isActive());
    roleRepository.save(role);
    log.info("==== toggle success =====");
  }

  private Role getById(Long id) {
    return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Role", id));
  }
}
