package org.example.sample_project.service;

import org.example.sample_project.dto.request.CreateLeaveRequest;
import org.example.sample_project.dto.request.ReviewLeaveRequest;
import org.example.sample_project.dto.response.LeaveResponse;
import org.example.sample_project.entity.LeaveRequest;

import java.util.List;

public interface LeaveRequestService {

    LeaveResponse create(CreateLeaveRequest request);

    LeaveResponse findById(Long id);

    List<LeaveResponse> findMyRequests();

    List<LeaveResponse> findPendingRequests();

    LeaveResponse review(
            Long id,
            ReviewLeaveRequest request
    );
}