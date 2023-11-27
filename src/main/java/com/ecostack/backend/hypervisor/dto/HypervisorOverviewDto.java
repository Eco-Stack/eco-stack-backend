package com.ecostack.backend.hypervisor.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class  HypervisorOverviewDto {
    List<HypervisorMetricDto> cpuUsageAverageMetrics;
    List<HypervisorMetricDto> memoryUsageAverageMetrics;
    List<HypervisorMetricDto> diskUsageAverageMetrics;
}
