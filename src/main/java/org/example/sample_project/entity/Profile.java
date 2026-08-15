package org.example.sample_project.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "profiles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = true)
    private String phoneNumber;
    @Column(length = 500)
    private String address;
    @Column
    private LocalDate dateOfBirth;
    @Column
    private String emergencyContactName;
    @Column
    private String emergencyContactPhone;
    @Column
    private String avatarUrl;

    @OneToOne(mappedBy = "profile")
    private Employee employee;
}