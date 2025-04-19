package org.ahmet.mysqlkafka.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "employee_projects")
@Data
public class EmployeeProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}