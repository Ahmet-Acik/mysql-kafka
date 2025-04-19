package org.ahmet.mysqlkafka.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DashboardController {
    private final List<String> eventFeed = new ArrayList<>();

    @KafkaListener(topics = {"department_topic", "employee_topic", "project_topic"}, groupId = "group_id")
    public void listenToEvents(String message) {
        eventFeed.add(message);
    }

    @GetMapping("/events")
    public List<String> getEvents() {
        return eventFeed;
    }
}