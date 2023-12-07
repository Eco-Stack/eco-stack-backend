package com.ecostack.backend.repository;

import com.ecostack.backend.model.CloudProject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudProjectRepository extends MongoRepository<CloudProject, String> {
}
