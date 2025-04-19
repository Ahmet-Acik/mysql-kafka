package org.ahmet.mysqlkafka.repository;

import org.ahmet.mysqlkafka.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}