package com.ecostack.backend.hypervisor.metric;

import com.ecostack.backend.metric.MetricValues;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@ToString
@Builder
@Getter
@Document(collection = "HypervisorMetric")
public class HypervisorMetric {

    @Id
    private String id;
    private String name;
    private LocalDateTime dateTime;
    private MetricValues metricValues;
}
