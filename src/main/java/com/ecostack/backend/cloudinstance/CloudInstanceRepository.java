package com.ecostack.backend.cloudinstance;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudInstanceRepository extends MongoRepository<CloudInstance, String> {
}
