package com.ecostack.backend.repository;

import com.ecostack.backend.model.CloudInstance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudInstanceRepository extends MongoRepository<CloudInstance, String> {
}
