package com.ecostack.backend.project;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CloudProjectRepository extends MongoRepository<CloudProject, String> {
}
