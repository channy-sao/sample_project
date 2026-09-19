package org.example.sample_project.repository;

import org.example.sample_project.constant.enums.LeaveStatus;
import org.example.sample_project.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRequestRepository
        extends JpaRepository<LeaveRequest, Long> {

    List<LeaveRequest> findByEmployeeIdOrderByStartDateDesc(Long employeeId);

    List<LeaveRequest> findByStatusOrderByStartDateAsc(LeaveStatus status);

    boolean existsByEmployeeIdAndStatusAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            Long employeeId,
            LeaveStatus status,
            java.time.LocalDate endDate,
            java.time.LocalDate startDate
    );
}