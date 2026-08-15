package org.example.sample_project.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.sample_project.constant.enums.ContractType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contracts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String contractNumber; // e.g., "CTR-2026-88"

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ContractType contractType; // PERMANENT, FIXED_TERM, INTERNSHIP

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal baseSalary;

    @OneToOne(mappedBy = "contract")
    private Employee employee;
}