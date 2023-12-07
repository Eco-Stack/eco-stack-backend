package com.ecostack.backend.hypervisor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HypervisorRepository extends MongoRepository<Hypervisor, String> {

}
