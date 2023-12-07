package com.ecostack.backend.repository;

import com.ecostack.backend.model.HypervisorMetric;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HypervisorMetricRepository extends MongoRepository<HypervisorMetric, String> {
}
