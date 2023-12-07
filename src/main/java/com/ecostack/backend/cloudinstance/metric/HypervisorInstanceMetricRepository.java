package com.ecostack.backend.cloudinstance.metric;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HypervisorInstanceMetricRepository extends MongoRepository<HypervisorInstanceMetric, String> {
}
