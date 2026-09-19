package org.example.sample_project.repository;

import org.example.sample_project.entity.LeaveEntitlement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeaveEntitlementRepository
        extends JpaRepository<LeaveEntitlement, Long> {

    Optional<LeaveEntitlement> findByEmployeeIdAndLeaveTypeIdAndYear(
            Long employeeId,
            Long leaveTypeId,
            Integer year
    );

    List<LeaveEntitlement> findByEmployeeIdAndYear(
            Long employeeId,
            Integer year
    );
}