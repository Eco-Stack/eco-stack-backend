package com.ecostack.backend.dto.cloudinstance;

import com.ecostack.backend.model.MetricValues;
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
