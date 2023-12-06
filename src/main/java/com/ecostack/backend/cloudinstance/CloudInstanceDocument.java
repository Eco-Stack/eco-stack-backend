package com.ecostack.backend.cloudinstance;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Getter
@Document(collection = "CloudInstance")
public class CloudInstanceDocument {

    @Id
    private String id;
    private List<String> cpuUtilizationMetricIds;
    private List<String> memoryUtilizationMetricIds;
    private List<String> diskUsageMetricIds;
}
