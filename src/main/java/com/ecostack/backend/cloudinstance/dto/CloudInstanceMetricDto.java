package com.ecostack.backend.cloudinstance.dto;

import com.ecostack.backend.metric.MetricValues;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CloudInstanceMetricDto {
    List<MetricValues> cpuUtilizationMetricValues;
    List<MetricValues> memoryUtilizationMetricValues;
    List<MetricValues> diskUtilizationMetricValues;
}
