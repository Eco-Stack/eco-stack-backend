package com.ecostack.backend.metric;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface MetricRepository extends MongoRepository<Metric, String> {
}
