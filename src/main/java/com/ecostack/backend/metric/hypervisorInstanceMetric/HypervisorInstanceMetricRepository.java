package com.ecostack.backend.metric.hypervisorInstanceMetric;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HypervisorInstanceMetricRepository extends MongoRepository<HypervisorInstanceMetric, String> {
}
