package com.example.demo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface FeatureAlertRepository extends MongoRepository<FeatureAlert, String> {
}
