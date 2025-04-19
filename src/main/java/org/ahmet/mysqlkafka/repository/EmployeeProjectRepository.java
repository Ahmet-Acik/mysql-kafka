package org.ahmet.mysqlkafka.repository;

import org.ahmet.mysqlkafka.model.EmployeeProject;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeProjectRepository extends CrudRepository<EmployeeProject, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO employee_projects (employee_id, project_id) VALUES (:employeeId, :projectId)", nativeQuery = true)
    void assignEmployeeToProject(Integer employeeId, Integer projectId);
}