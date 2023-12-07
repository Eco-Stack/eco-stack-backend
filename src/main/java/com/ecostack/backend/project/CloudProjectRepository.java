package com.ecostack.backend.project;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudProjectRepository extends MongoRepository<CloudProject, String> {
}
