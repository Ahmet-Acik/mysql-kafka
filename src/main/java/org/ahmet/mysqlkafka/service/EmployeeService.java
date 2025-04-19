package org.ahmet.mysqlkafka.service;

        import org.ahmet.mysqlkafka.model.Employee;
        import org.ahmet.mysqlkafka.repository.EmployeeRepository;
        import org.springframework.kafka.core.KafkaTemplate;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.stream.Collectors;
        import java.util.stream.StreamSupport;

        @Service
        public class EmployeeService {
            private final EmployeeRepository employeeRepository;
            private final KafkaProducerService kafkaProducerService;
            private final KafkaTemplate<String, String> kafkaTemplate;

            public EmployeeService(EmployeeRepository employeeRepository, KafkaProducerService kafkaProducerService, KafkaTemplate<String, String> kafkaTemplate) {
                this.employeeRepository = employeeRepository;
                this.kafkaProducerService = kafkaProducerService;
                this.kafkaTemplate = kafkaTemplate;
            }

            public void assignEmployeeToProject(Long employeeId, Long projectId) {
                String message = "Assign employee " + employeeId + " to project " + projectId;
                kafkaTemplate.send("assignment_topic", message);
            }

            public List<Employee> getAllEmployees() {
                return StreamSupport.stream(employeeRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());
            }

            public Employee saveEmployee(Employee employee) {
                Employee savedEmployee = employeeRepository.save(employee);
                kafkaProducerService.sendMessage("employee_topic", "Employee saved: " + savedEmployee.getName());
                return savedEmployee;
            }
        }