package org.example.sample_project.repository;

import org.example.sample_project.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT COALESCE(MAX(emp.id), 0) FROM Employee emp")
    Long getLastEmployeeId();


    @Query("SELECT e FROM Employee e WHERE " +
            "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :filter, '%')) OR " +
            "LOWER(e.lastName) LIKE LOWER(CONCAT('%', :filter, '%'))")
    Page<Employee> findByNameContainingIgnoreCase(@Param("filter") String filter, Pageable pageable);

    Optional<Employee> findByEmail(String username);
}
