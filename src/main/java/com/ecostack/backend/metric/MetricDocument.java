package com.ecostack.backend.metric;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Builder
@Getter
@Document(collection = "metric")
public class MetricDocument {

    @Id
    private String id;
    private String instanceId;
    private String name;
    private LocalDateTime dateTime;
    private Metrics metrics;
}
