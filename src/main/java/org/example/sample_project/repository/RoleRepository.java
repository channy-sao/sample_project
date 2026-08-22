package org.example.sample_project.repository;

import org.example.sample_project.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(String name);

  @Query("SELECT r FROM Role r where r.name ilike :filter or r.description ilike :filter")
  Page<Role> filter(@Param("filter") String filter, Pageable pageable);
}
