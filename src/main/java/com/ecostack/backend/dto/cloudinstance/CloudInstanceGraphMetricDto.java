package com.ecostack.backend.dto.cloudinstance;

import com.ecostack.backend.dto.metric.MetricValuesDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CloudInstanceGraphMetricDto {
    List<MetricValuesDto> cpuUtilizationMetricValues;
    List<MetricValuesDto> memoryUtilizationMetricValues;
    List<MetricValuesDto> diskUtilizationMetricValues;
}
