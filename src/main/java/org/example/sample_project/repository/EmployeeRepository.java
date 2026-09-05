package org.example.sample_project.repository;

import org.example.sample_project.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT COALESCE(MAX(emp.id), 0) FROM Employee emp")
    Long getLastEmployeeId();
}
