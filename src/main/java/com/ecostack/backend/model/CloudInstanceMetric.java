package com.ecostack.backend.model;

import com.ecostack.backend.model.MetricValues;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@ToString
@Builder
@Getter
@Document(collection = "CloudInstanceMetric")
public class CloudInstanceMetric {

    @Id
    private String id;
    private String name;
    private LocalDateTime dateTime;
    private MetricValues metricValues;
}
