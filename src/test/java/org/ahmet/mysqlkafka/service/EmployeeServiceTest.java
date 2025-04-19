package org.ahmet.mysqlkafka.service;

        import org.ahmet.mysqlkafka.model.Employee;
        import org.ahmet.mysqlkafka.repository.EmployeeRepository;
        import org.junit.jupiter.api.Test;
        import org.mockito.Mockito;
        import org.springframework.kafka.core.KafkaTemplate;

        import java.util.Collections;
        import java.util.List;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.mockito.Mockito.*;

        class EmployeeServiceTest {

            @Test
            void testGetAllEmployees() {
                EmployeeRepository mockRepository = mock(EmployeeRepository.class);
                KafkaProducerService mockKafkaService = mock(KafkaProducerService.class);
                KafkaTemplate<String, String> mockKafkaTemplate = mock(KafkaTemplate.class);
                EmployeeService employeeService = new EmployeeService(mockRepository, mockKafkaService, mockKafkaTemplate);

                Employee employee = new Employee();
                employee.setName("John Doe");
                when(mockRepository.findAll()).thenReturn(Collections.singletonList(employee));

                List<Employee> employees = employeeService.getAllEmployees();
                assertEquals(1, employees.size());
                assertEquals("John Doe", employees.get(0).getName());
            }
        }