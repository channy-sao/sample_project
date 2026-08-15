package org.example.sample_project.config;

import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sample_project.constant.enums.PermissionEnum;
import org.example.sample_project.entity.Permission;
import org.example.sample_project.entity.Role;
import org.example.sample_project.entity.User;
import org.example.sample_project.repository.PermissionRepository;
import org.example.sample_project.repository.RoleRepository;
import org.example.sample_project.repository.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
@Order(1)
public class DataInitializer implements ApplicationRunner {
  private final PermissionRepository permissionRepository;
  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  private static final String SUPER_ADMIN_ROLE = "SUPER_ADMIN";
  private static final String ADMIN_ROLE = "ADMIN";
  private static final String USER_ROLE = "USER";

  private void seedPermissions() {

    try {
      for (var permissionEnum : PermissionEnum.values()) {
        permissionRepository
            .findByName(permissionEnum)
            .orElseGet(
                () ->
                    permissionRepository.save(
                        Permission.builder()
                            .name(permissionEnum)
                            .category(permissionEnum.getCategory())
                            .description(permissionEnum.getDescription())
                            .build()));
        log.info("Permission {} has been created", permissionEnum.name());
      }
    } catch (Exception _) {
      log.info("Permissions not created");
    }
  }

  private void seedSuperAdminRole() {
    try {
      // Create a super admin role
      roleRepository
          .findByName(SUPER_ADMIN_ROLE)
          .ifPresentOrElse(
              d -> log.info("Role {} has been created", SUPER_ADMIN_ROLE),
              () ->
                  roleRepository.save(
                      Role.builder()
                          .name(SUPER_ADMIN_ROLE)
                          .permissions(new HashSet<>(permissionRepository.findAll()))
                          .isActive(true)
                          .build()));

      // Create an admin role
      roleRepository
          .findByName(ADMIN_ROLE)
          .ifPresentOrElse(
              _ -> log.info("Role admin has been created"),
              () ->
                  roleRepository.save(
                      Role.builder()
                          .name(ADMIN_ROLE)
                          .permissions(new HashSet<>(permissionRepository.findAll()))
                          .isActive(true)
                          .build()));

      roleRepository
          .findByName(USER_ROLE)
          .ifPresentOrElse(
              _ -> log.info("Role user has been created"),
              () ->
                  roleRepository.save(
                      Role.builder().name(USER_ROLE).permissions(Set.of()).isActive(true).build()));
    } catch (Exception _) {
      log.info("Roles not created");
    }
  }

  private void initSuperAdmin() {

    // Check if super admin already exists
    userRepository
        .findByEmail("admin@gmail.com")
        .ifPresentOrElse(
            _ -> log.info("Admin already exists"),
            () -> {
              Role role =
                  roleRepository
                      .findByName(DataInitializer.SUPER_ADMIN_ROLE)
                      .orElseThrow(() -> new RuntimeException("Super admin role is not found"));
              // Create a super admin user
              User admin =
                  User.builder()
                      .email("admin@gmail.com")
                      .password(passwordEncoder.encode("admin@123"))
                      .username("admin")
                      .enabled(true)
                      .roles(Set.of(role))
                      .build();

              userRepository.save(admin);

              log.info("Super Admin initialized successfully!");
            });

    userRepository
        .findByEmail("normaladmin@gmail.com")
        .ifPresentOrElse(
            _ -> log.info("Normal Admin already exists"),
            () -> {
              Role role = roleRepository.findByName(DataInitializer.ADMIN_ROLE).orElseThrow();
              // Create a normal admin user
              User normalAdmin =
                  User.builder()
                      .email("normaladmin@gmail.com")
                      .password(passwordEncoder.encode("admin@123"))
                      .username("normaladmin")
                      .enabled(true)
                      .roles(Set.of(role))
                      .build();

              userRepository.save(normalAdmin);

              log.info("Normal Admin initialized successfully!");
            });
  }

  @Transactional
  @Override
  public void run(ApplicationArguments args) throws Exception {
    seedPermissions();
    seedSuperAdminRole();
    initSuperAdmin();
  }
}
