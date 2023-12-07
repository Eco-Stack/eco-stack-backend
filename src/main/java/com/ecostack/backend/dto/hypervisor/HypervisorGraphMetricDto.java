package com.ecostack.backend.dto.hypervisor;

import com.ecostack.backend.dto.metric.MetricValuesDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class HypervisorGraphMetricDto {
    List<MetricValuesDto> cpuUtilizationMetricValues;
    List<MetricValuesDto> memoryUtilizationMetricValues;
}
