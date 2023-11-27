package com.ecostack.backend.hypervisor;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Getter
@Document(collection = "hypervisor")
public class HypervisorDocument {

    @Id
    private String id;
    private String name;
    private List<String> cloudInstanceIds;
}
