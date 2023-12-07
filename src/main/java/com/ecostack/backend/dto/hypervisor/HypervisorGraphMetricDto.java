package com.ecostack.backend.dto.hypervisor;

import com.ecostack.backend.model.MetricValues;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class HypervisorGraphMetricDto {
    List<MetricValues> cpuUtilizationMetricValues;
    List<MetricValues> memoryUtilizationMetricValues;
}
