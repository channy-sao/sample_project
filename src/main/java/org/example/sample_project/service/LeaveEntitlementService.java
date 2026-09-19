package org.example.sample_project.service;

import org.example.sample_project.dto.request.CreateLeaveEntitlementRequest;
import org.example.sample_project.dto.response.LeaveEntitlementResponse;
import org.example.sample_project.entity.LeaveEntitlement;

import java.util.List;

public interface LeaveEntitlementService {

    LeaveEntitlementResponse create(CreateLeaveEntitlementRequest request);

    LeaveEntitlementResponse findById(Long id);

    List<LeaveEntitlementResponse> findByEmployee(Long employeeId, Integer year);

    List<LeaveEntitlementResponse> findMyEntitlements(Integer year);

    LeaveEntitlementResponse findMyEntitlement(Long leaveTypeId, Integer year);

    double getAvailableDays(
            Long employeeId,
            Long leaveTypeId,
            Integer year
    );
}