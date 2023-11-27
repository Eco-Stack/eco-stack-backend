package com.ecostack.backend.cloudinstance.dto;

import com.ecostack.backend.metric.Metrics;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CloudInstanceMetric {
    Metrics cpuUsageMetrics;
    Metrics memoryUsageMetrics;
    Metrics diskUsageMetrics;
}
