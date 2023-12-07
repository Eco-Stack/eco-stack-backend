package com.ecostack.backend.metric.cloudinstance;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudInstanceMetricRepository extends MongoRepository<CloudInstanceMetric, String> {
}
