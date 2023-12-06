package com.ecostack.backend.hypervisor;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HypervisorRepository extends MongoRepository<Hypervisor, String> {

}
