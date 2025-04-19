package org.ahmet.mysqlkafka.repository;

import org.ahmet.mysqlkafka.model.EmployeeProject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Long> {
}