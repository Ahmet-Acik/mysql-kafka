package org.ahmet.mysqlkafka.repository;

import org.ahmet.mysqlkafka.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}