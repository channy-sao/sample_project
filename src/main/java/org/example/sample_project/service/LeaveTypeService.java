package org.example.sample_project.service;

import org.example.sample_project.dto.request.CreateLeaveTypeRequest;
import org.example.sample_project.entity.LeaveType;

import java.util.List;

public interface LeaveTypeService {

    LeaveType create(CreateLeaveTypeRequest request);

    List<LeaveType> findAll();

    LeaveType findById(Long id);

    LeaveType update(Long id, CreateLeaveTypeRequest request);

    void delete(Long id);
}