package org.example.sample_project.service.impl;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sample_project.dto.request.ContractRequest;
import org.example.sample_project.dto.request.EmployeeCreateRequest;
import org.example.sample_project.dto.request.EmployeeUpdateRequest;
import org.example.sample_project.dto.request.ProfileRequest;
import org.example.sample_project.dto.response.EmployeeResponse;
import org.example.sample_project.entity.Contract;
import org.example.sample_project.entity.Department;
import org.example.sample_project.entity.Employee;
import org.example.sample_project.entity.Position;
import org.example.sample_project.entity.Profile;
import org.example.sample_project.entity.User;
import org.example.sample_project.exception.ResourceNotFoundException;
import org.example.sample_project.mapper.EmployeeMapper;
import org.example.sample_project.repository.ContractRepository;
import org.example.sample_project.repository.DepartmentRepository;
import org.example.sample_project.repository.EmployeeRepository;
import org.example.sample_project.repository.PositionRepository;
import org.example.sample_project.repository.ProfileRepository;
import org.example.sample_project.repository.UserRepository;
import org.example.sample_project.service.EmployeeService;
import org.example.sample_project.service.FileManagerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {
  private final ContractRepository contractRepository;
  private final PositionRepository positionRepository;
  private final DepartmentRepository departmentRepository;
  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;
  private final EmployeeRepository employeeRepository;
  private final ProfileRepository profileRepository;
  private final FileManagerService fileManagerService;

  private static final String DEFAULT_PASSWORD = "123456";

  @Transactional(rollbackFor = Exception.class)
  @Override
  public EmployeeResponse createEmployee(EmployeeCreateRequest employeeCreateRequest) {

    // 1, get department
    Department department =
        departmentRepository
            .findById(employeeCreateRequest.getDepartmentId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Department", employeeCreateRequest.getDepartmentId()));

    // 2, get position

    Position position =
        positionRepository
            .findById(employeeCreateRequest.getPositionId())
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Position", employeeCreateRequest.getPositionId()));

    // 3, create user

    final User user = createUser(employeeCreateRequest);

    // 4, create contract
    final Contract contract = createContract(employeeCreateRequest);

    // 5, create profile

    final Profile profile = createProfile(employeeCreateRequest);

    // 6, create employee
    Employee lineManager =
        employeeRepository.findById(employeeCreateRequest.getLineManagerId()).orElse(null);

    Employee employee =
        Employee.builder()
            .contract(contract)
            .department(department)
            .employeeCode(getEmployeeCode())
            .email(employeeCreateRequest.getEmail())
            .firstName(employeeCreateRequest.getFirstName())
            .lastName(employeeCreateRequest.getLastName())
            .profile(profile)
            .user(user)
            .joinDate(employeeCreateRequest.getJoinDate())
            .lineManager(lineManager)
            .position(position)
            .build();
    Employee saved = employeeRepository.save(employee);
    log.info("Success create employee");
    return EmployeeMapper.toResponse(saved);
  }

  @Transactional(readOnly = true)
  @Override
  public EmployeeResponse getByEmployeeId(Long employeeId) {
    Employee employee =
        employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee", employeeId));
    return EmployeeMapper.toResponse(employee);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public EmployeeResponse update(Long id, EmployeeUpdateRequest updateRequest) {
    Employee employee =
        employeeRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee", id));

    Employee lineManager =
        employeeRepository.findById(updateRequest.getLineManagerId()).orElse(null);

    Position position =
        positionRepository
            .findById(updateRequest.getPositionId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Position", updateRequest.getPositionId()));

    Department department =
        departmentRepository
            .findById(updateRequest.getDepartmentId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Department", updateRequest.getDepartmentId()));

    Contract contract = updateContract(updateRequest, employee);

    Profile profile = updateProfile(updateRequest, employee);

    employee.setFirstName(updateRequest.getFirstName());
    employee.setLastName(updateRequest.getLastName());
    employee.setLineManager(lineManager);
    employee.setJoinDate(updateRequest.getJoinDate());
    employee.setContract(contract);
    employee.setPosition(position);
    employee.setDepartment(department);
    employee.setProfile(profile);

    Employee updated = employeeRepository.save(employee);
    return EmployeeMapper.toResponse(updated);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void delete(Long id) {
    employeeRepository.deleteById(id);
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public void updateStatus(Long id) {
    Employee employee =
        employeeRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee", id));
    boolean enabled = employee.getUser().isEnabled();
    employee.getUser().setEnabled(!enabled);
    employeeRepository.save(employee);
  }

  @Transactional(readOnly = true)
  @Override
  public Page<EmployeeResponse> getEmployees(int page, int size, String filter) {
    Pageable pageable = PageRequest.of(page - 1, size);
    Page<Employee> employeePage =
        employeeRepository.findByNameContainingIgnoreCase(filter, pageable);

    return employeePage.map(EmployeeMapper::toResponse);
  }

  @Transactional
  @Override
  public String uploadFile(MultipartFile file, Long id) {
    Employee employee =
        employeeRepository
            .findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Employee", id));

    String path = fileManagerService.upload(file, "employees");

    employee.getProfile().setAvatarUrl(path);
    employeeRepository.save(employee);
    return path;
  }

  private String getEmployeeCode() {
    var now = LocalDateTime.now();
    var lastEmployeeId = employeeRepository.getLastEmployeeId();

    // %04d pads the number with leading zeros until it is exactly 4 digits long
    return String.format("EMP-%d-%04d", now.getYear(), lastEmployeeId + 1);
  }

  private User createUser(EmployeeCreateRequest employeeCreaterequest) {

    User user =
        User.builder()
            .enabled(true)
            .username(getUserNameFormEmail(employeeCreaterequest.getEmail()))
            .email(employeeCreaterequest.getEmail())
            .password(passwordEncoder.encode(DEFAULT_PASSWORD))
            .accountNonLocked(true)
            .build();
    return userRepository.save(user);
  }

  private Contract createContract(EmployeeCreateRequest employeeCreateRequest) {
    ContractRequest contractRequest = employeeCreateRequest.getContract();
    Contract contract =
        Contract.builder()
            .baseSalary(contractRequest.getBaseSalary())
            .contractNumber(contractRequest.getContractNumber())
            .startDate(contractRequest.getStartDate())
            .endDate(contractRequest.getEndDate())
            .contractType(contractRequest.getContractType())
            .build();
    return contractRepository.save(contract);
  }

  private Contract updateContract(EmployeeUpdateRequest employeeCreateRequest, Employee employee) {
    ContractRequest contractRequest = employeeCreateRequest.getContract();

    Contract contract =
        contractRepository
            .findById(employee.getContract().getId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Contract", employee.getContract().getId()));

    contract.setBaseSalary(contractRequest.getBaseSalary());
    contract.setContractNumber(contractRequest.getContractNumber());
    contract.setContractType(contractRequest.getContractType());
    contract.setStartDate(contractRequest.getStartDate());
    return contractRepository.save(contract);
  }

  private Profile createProfile(EmployeeCreateRequest employeeCreateRequest) {
    ProfileRequest profileRequest = employeeCreateRequest.getProfile();
    Profile profile =
        Profile.builder()
            .address(profileRequest.getAddress())
            .avatarUrl(profileRequest.getAvatarUrl())
            .dateOfBirth(profileRequest.getDateOfBirth())
            .emergencyContactName(profileRequest.getEmergencyContactName())
            .emergencyContactPhone(profileRequest.getEmergencyContactPhone())
            .phoneNumber(profileRequest.getPhoneNumber())
            .build();
    return profileRepository.save(profile);
  }

  private Profile updateProfile(EmployeeUpdateRequest updateRequest, Employee employee) {
    ProfileRequest profileRequest = updateRequest.getProfile();

    Profile profile =
        profileRepository
            .findById(employee.getProfile().getId())
            .orElseThrow(
                () -> new ResourceNotFoundException("Profile", employee.getPosition().getId()));
    profile.setAddress(profileRequest.getAddress());
    profile.setAvatarUrl(profileRequest.getAvatarUrl());
    profile.setDateOfBirth(profileRequest.getDateOfBirth());
    profile.setEmergencyContactName(profileRequest.getEmergencyContactName());
    profile.setEmergencyContactPhone(profileRequest.getEmergencyContactPhone());
    profile.setPhoneNumber(profileRequest.getPhoneNumber());
    return profileRepository.save(profile);
  }

  private String getUserNameFormEmail(String email) {
    if (email == null || !email.contains("@")) {
      return ""; // ឬ throw new IllegalArgumentException("Invalid email format");
    }
    // កាត់យកអក្សរចាប់ពីដើម រហូតដល់អក្សរ @
    return email.substring(0, email.indexOf("@"));
  }
}
