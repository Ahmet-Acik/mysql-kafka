package org.ahmet.mysqlkafka.service;

import org.ahmet.mysqlkafka.model.Department;
import org.ahmet.mysqlkafka.repository.DepartmentRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public DepartmentService(DepartmentRepository departmentRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.departmentRepository = departmentRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department saveDepartment(Department department) {
        Department savedDepartment = departmentRepository.save(department);
        kafkaTemplate.send("department_topic", "New department created: " + savedDepartment.getName());
        return savedDepartment;
    }
}