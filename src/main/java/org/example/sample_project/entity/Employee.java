package org.example.sample_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.sample_project.entity.base.UserAuditableEntity;

import java.time.LocalDate;

@Entity
@Table(name = "employees", indexes = {
    @Index(name = "idx_emp_code", columnList = "employeeCode"),
    @Index(name = "idx_emp_email", columnList = "email")
})
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Employee extends UserAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 30)
    private String employeeCode; // e.g., "EMP-2026-001"

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private LocalDate joinDate; // Company Onboarding Date

    // --- Authentication Account Link ---
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true)
    private User user;

    // --- Organization Relationships ---
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    // Self-referential line manager mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "line_manager_id")
    private Employee lineManager;

    // --- Personal & Contract Details ---
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    private Profile profile;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;
}