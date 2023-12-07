package com.ecostack.backend.repository;

import com.ecostack.backend.model.CloudInstanceMetric;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudInstanceMetricRepository extends MongoRepository<CloudInstanceMetric, String> {
}
