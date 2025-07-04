package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController

public class FeatureAlertController {
    @Autowired
    private FeatureAlertRepository repository;
    @Autowired
    private FeatureAlertScheduler scheduler;

    @GetMapping("/alerts")
    public List<FeatureAlert> getAlerts() {
        return repository.findAll();
    }

    @PostMapping("/alerts")
    public FeatureAlert createAlert(@RequestBody FeatureAlert alert) {
        FeatureAlert saved = repository.save(alert);
        scheduler.sendEmail(saved);
        return saved;
    }
}
