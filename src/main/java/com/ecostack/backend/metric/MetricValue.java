package com.ecostack.backend.metric;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@ToString
@Builder
@Getter
public class MetricValue {

    private Instant dateTime;
    private Double value;
}
