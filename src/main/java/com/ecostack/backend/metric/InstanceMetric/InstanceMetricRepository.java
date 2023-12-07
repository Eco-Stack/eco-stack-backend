package com.ecostack.backend.metric.InstanceMetric;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstanceMetricRepository extends MongoRepository<InstanceMetric, String> {
}
