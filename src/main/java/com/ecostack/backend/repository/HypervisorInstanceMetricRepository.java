package com.ecostack.backend.repository;

import com.ecostack.backend.model.HypervisorInstanceMetric;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HypervisorInstanceMetricRepository extends MongoRepository<HypervisorInstanceMetric, String> {
}
