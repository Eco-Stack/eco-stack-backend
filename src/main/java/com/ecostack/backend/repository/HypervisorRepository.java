package com.ecostack.backend.repository;

import com.ecostack.backend.model.Hypervisor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HypervisorRepository extends MongoRepository<Hypervisor, String> {

}
