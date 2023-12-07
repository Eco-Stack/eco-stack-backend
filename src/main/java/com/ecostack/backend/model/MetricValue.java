package com.ecostack.backend.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@ToString
@Getter
@Builder
public class MetricValue {

    private Instant dateTime;
    private double value;
}
