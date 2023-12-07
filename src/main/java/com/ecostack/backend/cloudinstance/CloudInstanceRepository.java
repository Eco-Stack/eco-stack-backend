package com.ecostack.backend.cloudinstance;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CloudInstanceRepository extends MongoRepository<CloudInstance, String> {
}
