package com.ecostack.backend.cloudinstance.dto;

import com.ecostack.backend.metric.MetricValues;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CloudInstanceMetric {
    MetricValues cpuUsageMetricValues;
    MetricValues memoryUsageMetricValues;
    MetricValues diskUsageMetricValues;
}
