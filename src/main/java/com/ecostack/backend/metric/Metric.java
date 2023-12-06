package com.ecostack.backend.metric;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@ToString
@Builder
@Getter
@Document(collection = "Metric")
public class Metric {

    @Id
    private String id;
    private String instanceId;
    private String name;
    private LocalDateTime dateTime;
    private MetricValues metricValues;
}
