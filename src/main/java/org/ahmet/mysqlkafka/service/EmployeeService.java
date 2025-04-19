package org.ahmet.mysqlkafka.service;

import org.ahmet.mysqlkafka.model.Employee;
import org.ahmet.mysqlkafka.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final KafkaProducerService kafkaProducerService;

    public EmployeeService(EmployeeRepository employeeRepository, KafkaProducerService kafkaProducerService) {
        this.employeeRepository = employeeRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);
        kafkaProducerService.sendMessage("employee_topic", "Employee saved: " + savedEmployee.getName());
        return savedEmployee;
    }
}