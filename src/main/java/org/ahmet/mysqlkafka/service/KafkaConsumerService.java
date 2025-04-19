package org.ahmet.mysqlkafka.service;

            import org.ahmet.mysqlkafka.repository.EmployeeProjectRepository;
            import org.springframework.kafka.annotation.KafkaListener;
            import org.springframework.stereotype.Service;

            @Service
            public class KafkaConsumerService {

                private final EmployeeProjectRepository employeeProjectRepository;

                public KafkaConsumerService(EmployeeProjectRepository employeeProjectRepository) {
                    this.employeeProjectRepository = employeeProjectRepository;
                }

                @KafkaListener(topics = {"department_topic", "employee_topic", "project_topic"}, groupId = "group_id")
                public void consumeMessage(String message) {
                    System.out.println("Log: " + message);
                }

                @KafkaListener(topics = "assignment_topic", groupId = "group_id")
                public void handleAssignment(String message) {
                    try {
                        // Step 1: Validate the message
                        if (message == null || !message.matches("Assign employee \\d+ to project \\d+")) {
                            throw new IllegalArgumentException("Invalid message format: " + message);
                        }

                        // Step 2: Parse the message
                        String[] parts = message.split(" ");
                        Integer employeeId = Integer.parseInt(parts[2]);
                        Integer projectId = Integer.parseInt(parts[5]);

                        // Step 3: Insert into the database
                        employeeProjectRepository.assignEmployeeToProject(employeeId, projectId);

                        System.out.println("Successfully processed task: " + message);
                    } catch (Exception e) {
                        // Step 4: Handle errors
                        System.err.println("Error processing message: " + message);
                        e.printStackTrace();
                    }
                }
            }