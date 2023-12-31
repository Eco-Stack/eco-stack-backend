package com.ecostack.backend.model;

import com.ecostack.backend.model.MetricValues;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@ToString
@Builder
@Getter
@Document(collection = "HypervisorInstanceMetric")
public class HypervisorInstanceMetric {

    @Id
    private String id;
    private String name;
    private LocalDate date;
    private MetricValues metricValues;
}
