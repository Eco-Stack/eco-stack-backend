package com.ecostack.backend.cloudinstance;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Getter
@Document(collection = "instance")
public class CloudInstanceDocument {

    @Id
    private String id;
    private List<String> cpuCoreMetricIds;
    private List<String> cpuUsageMetricIds;
    private List<String> memoryUsageInBytesMetricIds;
    private List<String> diskUsageMetricIds;
    private List<String> networkInputMetricIds;
    private List<String> networkOutputMetricIds;
}
