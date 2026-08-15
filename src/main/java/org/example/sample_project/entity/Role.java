package org.example.sample_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String name; // e.g., "ROLE_ADMIN", "ROLE_HR_MANAGER", "ROLE_EMPLOYEE"

    @Column
    private boolean isActive;

    @Column(length = 250)
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Permission> permissions= new HashSet<>();


}