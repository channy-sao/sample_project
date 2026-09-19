package org.example.sample_project.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.sample_project.dto.request.CreateLeaveTypeRequest;
import org.example.sample_project.entity.LeaveType;
import org.example.sample_project.repository.LeaveTypeRepository;
import org.example.sample_project.service.LeaveTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LeaveTypeServiceImpl implements LeaveTypeService {

    private final LeaveTypeRepository leaveTypeRepository;

    @Override
    public LeaveType create(CreateLeaveTypeRequest request) {

        if (leaveTypeRepository.existsByNameIgnoreCase(request.getName())) {
            throw new RuntimeException("Leave type already exists");
        }

        LeaveType leaveType = LeaveType.builder()
                .name(request.getName())
                .description(request.getDescription())
                .paid(request.isPaid())
                .active(true)
                .build();

        return leaveTypeRepository.save(leaveType);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LeaveType> findAll() {
        return leaveTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public LeaveType findById(Long id) {
        return leaveTypeRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Leave type not found"));
    }

    @Override
    public LeaveType update(
            Long id,
            CreateLeaveTypeRequest request
    ) {

        LeaveType leaveType = findById(id);

        leaveType.setName(request.getName());
        leaveType.setDescription(request.getDescription());
        leaveType.setPaid(request.isPaid());

        return leaveTypeRepository.save(leaveType);
    }

    @Override
    public void delete(Long id) {

        LeaveType leaveType = findById(id);

        leaveTypeRepository.delete(leaveType);
    }
}