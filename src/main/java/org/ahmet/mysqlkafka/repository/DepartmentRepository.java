package org.ahmet.mysqlkafka.repository;

import org.ahmet.mysqlkafka.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}